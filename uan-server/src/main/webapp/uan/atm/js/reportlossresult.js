/**
 *  reportlossresult.js
 *  功能    查看签约响应信息
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /reportlossresult
 *  前一路由 /reportlosscheckinfo
 *  后一路由 /uanindex
 *  输出变量 window.app.reportLossResultV
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
    var reportLossResultDomTemplate = '<a class="atm-btn btn-right btn-row-4" data-bind="click: returnToIndex">返&nbsp;&nbsp;回</a>\
    <div class="container-fluid" style="height: 100%;">\
      <h1 style="margin: 21px auto; width: 420px;">挂失成功！</h1>\
      <br/><br/><br/>\
      <div class="amt-checkinfo-bar form-horizontal">\
\
        <div class="form-group">\
          <div class="col-xs-4"><label>授权人姓名</label></div>\
          <div class="col-xs-8"><div class="info-value" data-bind="text:masterName"></div></div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-4"><label>授权人账号</label></div>\
          <div class="col-xs-8"><div class="info-value" data-bind="text:cardNum"></div></div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-12"><div class="info-value" data-bind="text:info"></div></div>\
        </div>\
      </div>\
\
    </div>';


  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var reportLossResultDomString = kendo.template(reportLossResultDomTemplate)({});

  // reportLossResult视图view modal
  var reportLossResultVM = kendo.observable({
    cardNum: '',
    info: '',
    masterName: '',
    // init: function () {
    //   console.log("view init", this.customerName);
    // },
    show: function () {
      var rciVM = app.reportLossCheckInfoV.model;
      // 判断是否已收到信息，否则跳转至/
      if(!rciVM.get('masterName')) {
        // 跳转路由至/
        app.redirectTo();
      }

      this.set('cardNum', rciVM.get('cardNum'));
      this.set('info', rciVM.get('info'));
      this.set('masterName', rciVM.get('masterName'));
    },
    hide: function () {
      this.set('cardNum', '');
      this.set('info', '');
      this.set('masterName', '');
    },
    returnToIndex: function () {
      router.navigate('/uanindex');
    },
  });

  // reportLossResult视图view，从字符串变量reportLossResultDomString中加载DOM结构，绑定ViewModel
  var reportLossResultV = new kendo.View(reportLossResultDomString, {
    model: reportLossResultVM, 
    // init: reportLossResultVM.init.bind(reportLossResultVM),
    show: reportLossResultVM.show.bind(reportLossResultVM),
    hide: reportLossResultVM.hide.bind(reportLossResultVM),
    returnToIndex: reportLossResultVM.returnToIndex.bind(reportLossResultVM)
  });

  // reportLossResult视图路由，渲染指定view
  router.route('/reportlossresult', function () {
    layout.showIn(layoutSelector,reportLossResultV);
  });

    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'reportLossResultV': reportLossResultV
    });

}));
