/**
 *  reportlossasset.js
 *  功能    挂失-授权人资产列表视图
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /reportlossasset
 *  前一路由 /reportlosslist
 *  后一路由 /reportlosscheckinfo
 *  输出变量 window.app.reportLossAssetV
 * 
 * Copyright Agriculture Bank of China.
 */
;(function(factory) {
    if (typeof define === 'function' && define.amd) {
      // AMD. Register as anonymous module.
      define(['jquery'], function(jQuery) {
        return factory(jQuery, window, document);
      });
    } else if (typeof module === 'object' && module.exports) {
      // Node. Does not work with strict CommonJS, but
      // only CommonJS-like enviroments that support module.exports,
      // like Node.
      module.exports = factory(require('jquery'), window, document);
    } else {
      // Browser globals.
      factory(jQuery, window, document);
    }
  }(function($, window, document, undefined) {
    'use strict';
  
    // 获取全局变量，若为空则对其初始化
    var kendo = window.kendo,
      app = window.app,
      layout = app.layout,
      router = app.router,
      layoutSelector = app.layoutSelector;
  
    // 构造视图DOM模板
    var reportLossAssetDomTemplate = '<a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">选择授权人</a>\
    <a class="atm-btn btn-right btn-row-1" data-bind="click: rowMoveUp"><i class="fa fa-arrow-up"></i></a>\
    <a class="atm-btn btn-right btn-row-2" data-bind="click: rowMoveDown"><i class="fa fa-arrow-down"></i></a>\
    <a class="atm-btn btn-right btn-row-4 btn-confirm" data-bind="click: checkinfo">挂失账户</a>\
\
    <div class="container-fluid">\
      <br/><br/><br/>\
      <h2 style="margin: 15px auto; width: 55%;">授权人签约账户</h2>\
      <div class="grid-asset" style="margin: 15px auto; width: 55%;"></div>\
    </div>';
  
    var gridRelativesTemplate = '<div data-role="grid"\
      data-selectable="row"\
      data-columns="[\
        {field: \'customerInformation.name\', title: \'姓名\', width: 120},\
        {field: \'code\', title: \'账号\'},\
        {field: \'bankOfDeposit\', title: \'开户行\', width: 200}\
      ]"\
      data-bind="source: datasource"\
    ></div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var reportLossAssetDomString = kendo.template(reportLossAssetDomTemplate)({});
  
    // reportLossAsset视图view modal
    var reportLossAssetVM = kendo.observable({
      // request
      appId: '',
      userId: '',
      customer: '',
      deviceNum: 'ATM模拟器',
      party: '',
      // respond
      assetVMs: [],
      assetGridView: null,
      gridWidget: null, // kendoGrid实例
      currentRowData: null, // 授权人资产表格当前选中行数据
      init: function () {
      },
      show: function () {
        var _this   = this, 
             config = app.proxy.findByIdCard,
               idVM = app.idcardloginV.model,
          relListVM = app.reportLossListV.model,
            relData = relListVM.get('currentRowData'); // 授权人表格当前选中行数据

        if(!relData) {
          console.error('未选择授权人！');
          // 跳转路由至 /
          app.redirectTo();
          return;
        }

        _this.set('appId', idVM.get('appId'));
        _this.set('customer', relData.masterId);
        _this.set('party', idVM.get('userId')); // TODO - 设置为userId值
        _this.set('userId', idVM.get('userId'));

        // 异步请求授权人列表数据
        udesk.ajax({
          type: config.type,
          url: config.url,
          contentType: config.contentType,
          data: JSON.stringify({
            appId: _this.get('appId'),
            data: _this.get('customer'),
            userId: _this.get('userId')
          })
        })
        .done(function(res) {
          if(res.statusCode != '200') {
            // TODO - 设置promise为false
            console.error(res.message);
            return;
          }
          // 受助人资产信息返回成功
          var assetVM = app.reportLossAssetV.model,
               data = res.data;

          for(var i in data) {
            assetVM.get('assetVMs').push($.extend(true, {}, data[i]));
          }

          //构造受助人列表
          assetVM.set('assetGridView', new kendo.View(gridRelativesTemplate, {
            model: kendo.observable({
              datasource: new kendo.data.DataSource({data: assetVM.get('assetVMs')})
            }),
            init: function() {
              assetVM.set('gridWidget',
                this.element.find('[data-role=grid]').data('kendoGrid'));
              // 默认选中第一行
              assetVM.get('gridWidget').select('tr:eq(0)');
            }
          }));
          assetVM.get('assetGridView').render('.grid-asset');

        }) // END OF done()
        .fail(function(jqXHR, textStatus, errorThrown) {
          // TODO - 提交返回失败
        });
  

      }, // END OF show()
      hide: function () {
        // 析构授权人表
        this.get('assetGridView').destroy();
        delete this.get('assetGridView');
        delete this.get('gridWidget');
        this.emptyArray(this.get('assetVMs'));
      },
      prevPage: function () {
        router.navigate("/reportlosslist");
      },
      emptyArray: function(array) {
        var i,length = array.length;
        for(i=0; i<length; i++) {
          array.pop();
        } // END OF for
      },
      getRowCounts: function() {
        return this.gridWidget.items().length;
      },
      getCurrentRow: function() {
        return $(this.gridWidget.select()[0]); // 返回第一个所有处于选中状态的行
      },
      rowMoveUp: function() {
        var $currentRow = this.getCurrentRow();
        if(!$currentRow.length) {
          // 如果当前没有行被选中，则强制选中最后一行并返回
          return this.gridWidget.select('tr:eq('+ this.getRowCounts()-1 +')');
        }
        var $targetRow = $currentRow.prev();

        return $targetRow.length ?
          this.gridWidget.select($targetRow) : $currentRow;
      }, // END OF rowMoveUp()
      rowMoveDown: function() {
        var $currentRow = this.getCurrentRow();
        if(!$currentRow.length) {
          // 如果当前没有行被选中，则强制选中第一行并返回
          return this.gridWidget.select('tr:eq(0)');
        }
        var $targetRow = $currentRow.next();

        return $targetRow.length ?
          this.gridWidget.select($targetRow) : $currentRow;
      }, // END OF rowMoveDown()
      checkinfo: function () {
        var $currentRow = this.getCurrentRow();

        if(!$currentRow.length) {
          console.error('当前未选中行！');
          return;
        }

        this.set('currentRowData', this.get('gridWidget').dataItem($currentRow));
        router.navigate('/reportlosscheckinfo');
      }, // END OF checkinfo()
    });
  
    // reportLossAsset视图view，从字符串变量reportLossAssetDomString中加载DOM结构，绑定ViewModel
    var reportLossAssetV = new kendo.View(reportLossAssetDomString, {
      model: reportLossAssetVM, 
      init: reportLossAssetVM.init.bind(reportLossAssetVM),
      show: reportLossAssetVM.show.bind(reportLossAssetVM),
      hide: reportLossAssetVM.hide.bind(reportLossAssetVM),
      prevPage: reportLossAssetVM.prevPage.bind(reportLossAssetVM),
      checkinfo: reportLossAssetVM.checkinfo.bind(reportLossAssetVM),
    });
  
    // reportLossAsset视图路由，渲染指定view
    router.route("/reportlossasset", function () {
      layout.showIn(layoutSelector,reportLossAssetV);
    });
  
      // 输出至全局变量app
      window.app = $.extend(true, app, {
        'reportLossAssetV': reportLossAssetV
      });
  
  }));
  