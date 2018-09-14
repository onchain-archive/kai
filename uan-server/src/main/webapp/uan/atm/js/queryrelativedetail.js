/**
 *  queryrelativedetail.js
 *  功能    查询-授权人资产详情视图
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /queryrelativedetail
 *  前一路由 /queryrelativeasset
 *  后一路由 /uanindex
 *  输出变量 window.app.withdrawresultV
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
      var queryRelativeDetailDomTemplate = '<a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">继续查询</a>\
      <a class="atm-btn btn-right btn-row-4" data-bind="click: returnToIndex">返&nbsp;&nbsp;回</a>\
      <div class="container-fluid">\
        <h1 style="margin: 21px auto; width: 580px;">您查询的授权人账户信息如下：</h1>\
        <br/>\
        <div class="amt-checkinfo-bar form-horizontal">\
          <div class="form-group">\
            <div class="col-xs-4"><span>姓   名</span></div>\
            <div class="col-xs-8">\
              <div class="info-value" data-bind="text:customerInformation.name"></div>\
            </div>\
          </div>\
\
          <div class="form-group">\
            <div class="col-xs-4"><span>账   号</span></div>\
            <div class="col-xs-8">\
              <div class="info-value" data-bind="text:code"></div>\
            </div>\
          </div>\
  \
          <div class="form-group">\
            <div class="col-xs-4"><span>电   话</span></div>\
            <div class="col-xs-8">\
              <div class="info-value" data-bind="text:customerInformation.phone"></div>\
            </div>\
          </div>\
\
          <div class="form-group">\
            <div class="col-xs-4"><span>余   额</span></div>\
            <div class="col-xs-8"><div class="info-value" data-bind="text:amt"></div></div>\
          </div>\
\
          <div class="form-group">\
            <div class="col-xs-4"><span>开户行</span></div>\
            <div class="col-xs-8">\
              <div class="info-value" data-bind="text:bankOfDeposit"></div>\
            </div>\
          </div>\
\
      </div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var queryRelativeDetailDomString = kendo.template(queryRelativeDetailDomTemplate)({});
  
    // queryRelativeDetail视图view modal
    var queryRelativeDetailVM = kendo.observable({
      amt: '',
      bankOfDeposit: '',
      code: '',
      customerInformation: {
        id: '',
        idCard: '',
        name: '',
        phone: '',
        state: '',
        type: ''
      },
      id: '',
      idCard: '',
      state: '',
      // init: function () {
      // },
      show: function () {
        var data = app.queryRelativeAssetV.model.get('currentRowData');
        // 判断assetV授权人资产表格是否已选中行，否则跳转至 /queryrelativeasset
        if(!data) {
          // 跳转路由至 /queryrelativeasset
          app.redirectTo('/queryrelativeasset');
        }

        this.set('amt', data.amt);
        this.set('bankOfDeposit', data.bankOfDeposit);
        this.set('code', data.code);
        this.set('customerInformation', data.customerInformation);
        this.set('id', data.id);
        this.set('idCard', data.idCard);
        this.set('state', data.state);
      },
      hide: function () {
        this.set('amt', '');
        this.set('bankOfDeposit', '');
        this.set('code', '');
        this.set('customerInformation', {});
        this.set('id', '');
        this.set('idCard', '');
        this.set('state', '');
      },
      prevPage: function () {
        router.navigate('/queryrelativeasset');
      },
      returnToIndex: function () {
        router.navigate('/uanindex');
      },
    });
  
    // queryRelativeDetail视图view，从字符串变量queryRelativeDetailDomString中加载DOM结构，绑定ViewModel
    var queryRelativeDetailV = new kendo.View(queryRelativeDetailDomString, {
      model: queryRelativeDetailVM, 
      // init: queryRelativeDetailVM.init.bind(queryRelativeDetailVM),
      show: queryRelativeDetailVM.show.bind(queryRelativeDetailVM),
      hide: queryRelativeDetailVM.hide.bind(queryRelativeDetailVM),
      prevPage: queryRelativeDetailVM.prevPage.bind(queryRelativeDetailVM),
      returnToIndex: queryRelativeDetailVM.returnToIndex.bind(queryRelativeDetailVM)
    });
  
    // queryRelativeDetail视图路由，渲染指定view
    router.route('/queryrelativedetail', function () {
      layout.showIn(layoutSelector,queryRelativeDetailV);
    });
  
      // 输出至全局变量app
      window.app = $.extend(true, app, {
        'queryRelativeDetailV': queryRelativeDetailV
      });
  
  }));
  