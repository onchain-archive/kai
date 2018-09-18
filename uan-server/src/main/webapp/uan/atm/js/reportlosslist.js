/**
 *  reportlosslist.js
 *  功能    挂失-授权人列表视图
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /reportlosslist
 *  前一路由 /uanindex
 *  后一路由 /reportlosslistdetail
 *  输出变量 window.app.reportLossListV
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
    var reportLossListDomTemplate = '<a class="atm-btn btn-right btn-row-1" data-bind="click: rowMoveUp"><i class="fa fa-arrow-up"></i></a>\
    <a class="atm-btn btn-right btn-row-2" data-bind="click: rowMoveDown"><i class="fa fa-arrow-down"></i></a>\
    <a class="atm-btn btn-right btn-row-4 btn-confirm" data-bind="click: queryRelativeAssets">选择/Select</a>\
    <a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">取消/Cancle</a>\
    <div class="container-fluid">\
      <div class="title">\
        <div class="zh-CN">请选择授权人</div>\
        <div class="en-US">Please select the authorizer.</div>\
      </div>\
\
      <div class="grid-relatives"></div>\
\
      <div class="text">\
        <div class="zh-CN">请使用<b>[↑] [↓]</b>键选择授权人<br>按<b>[选择]</b>键继续</div>\
        <div class="en-US">Please use <b>[↑] [↓]</b> to select the authorizer,<br> then press <b>[Select]</b> to continue.</div>\
      </div>\
    </div>';
  
    var gridRelativesTemplate = '<div data-role="grid"\
      data-selectable="row"\
      data-columns="[\
        {field: \'order\', title: \'No.\', width: 55},\
        {field: \'slaveName\', title: \'姓名/Name\', width: 125},\
        {field: \'relationship\', title: \'关系/Relationship\', width: 180},\
        {field: \'masterId\', title: \'身份证号/IdCard No.\'}\
      ]"\
      data-bind="source: datasource"\
    ></div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var reportLossListDomString = kendo.template(reportLossListDomTemplate)({});
  
    // reportLossList视图view modal
    var reportLossListVM = kendo.observable({
      // request
      appId: '',
      userId: '',
      // respond
      relativeVMs: [],
      relativeGridView: null,
      gridWidget: null, // kendoGrid实例
      currentRowData: null, // 授权人表格当前选中行数据
      init: function () {
      },
      show: function () {
        var _this = this, 
           config = app.proxy.findMasters,
             idMV = app.idcardloginV.model;

        // 判断是否已登录，否则跳转至 /
        if(!idMV.get('name')) {
          // 跳转路由至 /
          app.redirectTo();
        }

        _this.set('appId', idMV.get('appId'));
        _this.set('userId', idMV.get('.userId'));

        if(_this.get('relativeVMs').length) {
          _this.emptyArray(this.get('relativeVMs'))
        }

        // 异步请求授权人列表数据
        udesk.ajax({
          type: config.type,
          url: config.url,
          contentType: config.contentType,
          data: JSON.stringify({
            appId: _this.get('appId'),
            data: _this.get('userId'),
            userId: _this.get('userId')
          })
        })
        .done(function(res) {
          if(res.statusCode != '200') { 
            // TODO - 设置promise为false
            console.error(res.message);
            return;
          }
          // 受助人列表返回成功，将返回的数据写入
          var listVM = app.reportLossListV.model,
               data = res.data;
              // listVM = app.reportLossListV.model;

          for(var i in data) {
            listVM.get('relativeVMs').push($.extend(true, {}, data[i]));
          }

          //构造受助人列表
          listVM.set('relativeGridView', new kendo.View(gridRelativesTemplate, {
            model: kendo.observable({
              datasource: new kendo.data.DataSource({data: listVM.get('relativeVMs')})
            }),
            init: function() {
              listVM.set('gridWidget',
                this.element.find('[data-role=grid]').data('kendoGrid'));
              // 默认选中第一行
              listVM.get('gridWidget').select('tr:eq(0)');
            } // END OF init()
          }));
          listVM.get('relativeGridView').render('.grid-relatives');

        }) // END OF done()
        .fail(function(jqXHR, textStatus, errorThrown) {
          // TODO - 提交返回失败
        });
  

      }, // END OF show()
      hide: function () {
        // 析构授权人表
        this.get('relativeGridView').destroy();
        delete this.get('relativeGridView');
        delete this.get('gridWidget');
        this.emptyArray(this.get('relativeVMs'));
      },
      prevPage: function () {
        router.navigate("/uanindex");
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
        return $(this.gridWidget.select()[0]); // 返回第一个处于选中状态的行
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
      },
      rowMoveDown: function() {
        var $currentRow = this.getCurrentRow();
        if(!$currentRow.length) {
          // 如果当前没有行被选中，则强制选中第一行并返回
          return this.gridWidget.select('tr:eq(0)');
        }
        var $targetRow = $currentRow.next();

        return $targetRow.length ?
          this.gridWidget.select($targetRow) : $currentRow;
      },
      queryRelativeAssets: function () {
        var $currentRow = this.getCurrentRow();

        if(!$currentRow.length) {
          console.error('当前未选中行！');
          return;
        }

        this.set('currentRowData', this.get('gridWidget').dataItem($currentRow));
        router.navigate('/reportlossasset');
      }, // queryRelativeAssets
    });
  
    // reportLossList视图view，从字符串变量reportLossListDomString中加载DOM结构，绑定ViewModel
    var reportLossListV = new kendo.View(reportLossListDomString, {
      model: reportLossListVM, 
      init: reportLossListVM.init.bind(reportLossListVM),
      show: reportLossListVM.show.bind(reportLossListVM),
      hide: reportLossListVM.hide.bind(reportLossListVM),
      prevPage: reportLossListVM.prevPage.bind(reportLossListVM),
      queryRelativeAssets: reportLossListVM.queryRelativeAssets.bind(reportLossListVM),
    });
  
    // reportLossList视图路由，渲染指定view
    router.route("/reportlosslist", function () {
      layout.showIn(layoutSelector,reportLossListV);
    });
  
      // 输出至全局变量app
      window.app = $.extend(true, app, {
        'reportLossListV': reportLossListV
      });
  
  }));
  