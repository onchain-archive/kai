/*
 * Copyright 2018 Liu Jiajie
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *  queryrelativeasset.js
 *  功能    查询-授权人资产列表视图
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /queryrelativeasset
 *  前一路由 /queryrelativelist
 *  后一路由 /queryrelativedetail
 *  输出变量 window.app.queryRelativeAssetV
 * 
 * 
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
    var queryRelativeAssetDomTemplate = '<a class="atm-btn btn-right btn-row-1" data-bind="click: rowMoveUp"><i class="fa fa-arrow-up"></i></a>\
    <a class="atm-btn btn-right btn-row-2" data-bind="click: rowMoveDown"><i class="fa fa-arrow-down"></i></a>\
    <a class="atm-btn btn-right btn-row-4 btn-confirm" data-bind="click: queryRelativeDetail">\
      <span class="zh-CN">查看</span>\
      <span class="en-US">View Detail</span>\
    </a>\
    <a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">取消/Cancle</a>\
    <div class="container-fluid">\
      <div class="title">\
        <div class="zh-CN">请选择签约账户</div>\
        <div class="en-US">Please select the contracted account.</div>\
      </div>\
\
      <div class="grid-asset"></div>\
\
      <div class="text">\
        <div class="zh-CN">请使用<b>[↑] [↓]</b>键选择账户<br>按<b>[查看]</b>键继续</div>\
        <div class="en-US">Please use <b>[↑] [↓]</b> to select the account,<br> then press <b>[View Detail]</b> to continue.</div>\
      </div>\
    </div>';
  
    var gridRelativesTemplate = '<div data-role="grid"\
      data-selectable="row"\
      data-columns="[\
        {field: \'customerInformation.name\', title: \'姓名/Name\', width: 125},\
        {field: \'code\', title: \'账号/Account No.\'},\
        {field: \'bankOfDeposit\', title: \'开户行/Bank of Deposit\', width: 240}\
      ]"\
      data-bind="source: datasource"\
    ></div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var queryRelativeAssetDomString = kendo.template(queryRelativeAssetDomTemplate)({});
  
    // queryRelativeAsset视图view modal
    var queryRelativeAssetVM = kendo.observable({
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
             config = app.proxy.inquireAssets,
               idVM = app.idcardloginV.model,
          relListVM = app.queryRelativeListV.model,
            relData = relListVM.get('currentRowData'); // 授权人表格当前选中行数据

        if(!relData) {
          console.error('未选择授权人！');
          // 跳转路由至 /queryrelativelist
          app.redirectTo('/queryrelativelist');
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
            data: {
              customer: _this.get('customer'),
              deviceNum: _this.get('deviceNum'),
              party: _this.get('party'),
            },
            userId: _this.get('userId')
          })
        })
        .done(function(res) {
          if(res.statusCode != '200') {
            // TODO - 设置promise为false
            console.error(res.message);
            // 跳转路由至 /queryrelativelist
            app.redirectTo('/queryrelativelist');
            return;
          }
          // 受助人资产信息返回成功
          var assetVM = app.queryRelativeAssetV.model,
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
        router.navigate("/queryrelativelist");
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
      queryRelativeDetail: function () {
        var $currentRow = this.getCurrentRow();

        if(!$currentRow.length) {
          console.error('当前未选中行！');
          return;
        }

        this.set('currentRowData', this.get('gridWidget').dataItem($currentRow));
        router.navigate('/queryrelativedetail');
      }, // END OF queryRelativeDetail()
    });
  
    // queryRelativeAsset视图view，从字符串变量queryRelativeAssetDomString中加载DOM结构，绑定ViewModel
    var queryRelativeAssetV = new kendo.View(queryRelativeAssetDomString, {
      model: queryRelativeAssetVM, 
      init: queryRelativeAssetVM.init.bind(queryRelativeAssetVM),
      show: queryRelativeAssetVM.show.bind(queryRelativeAssetVM),
      hide: queryRelativeAssetVM.hide.bind(queryRelativeAssetVM),
      prevPage: queryRelativeAssetVM.prevPage.bind(queryRelativeAssetVM),
      queryRelativeDetail: queryRelativeAssetVM.queryRelativeDetail.bind(queryRelativeAssetVM),
    });
  
    // queryRelativeAsset视图路由，渲染指定view
    router.route("/queryrelativeasset", function () {
      layout.showIn(layoutSelector,queryRelativeAssetV);
    });
  
      // 输出至全局变量app
      window.app = $.extend(true, app, {
        'queryRelativeAssetV': queryRelativeAssetV
      });
  
  }));
  