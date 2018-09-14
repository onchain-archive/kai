/**
 *  result.js
 *  功能      核对信息，提交签约请求
 *  作者       xxx
 *  日期       2018-08-xx
 *  当前路由 /result
 *  前一路由 /checkinfo
 *  后一路由 无
 *  输出变量 window.app.resultV
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
    var resultDomTemplate = '<div class="container-fluid">\
    <br>\
    <div class="form-horizontal">\
      <h1> 确认签约成功！</h>\
    </div>\
    <div class="add-bar">\
      <button class="btn btn-theme btn-add" data-bind="click: returnToIndex">点击返回</button>\
    </div>\
\
 </div>';


  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var resultDomString = kendo.template(resultDomTemplate)({
      
  });

  // result视图view modal
  var resultVM = kendo.observable({
    // init: function () {
    //   console.log("view init", this.customerName);
    // },
    // show: function () {
    //   console.log("view show", this.customerName);
    // },
    // hide: function () {
    //   console.log("view hide", this.customerName);
    // },
    returnToIndex: function () {
      router.navigate('');
    },
  });

  // result视图view，从字符串变量resultDomString中加载DOM结构，绑定ViewModel
  var resultV = new kendo.View(resultDomString, {
    model: resultVM, 
    // init: resultVM.init.bind(resultVM),
    // show: resultVM.show.bind(resultVM),
    // hide: resultVM.hide.bind(resultVM),
    returnToIndex: resultVM.returnToIndex.bind(resultVM)
  });

  // result视图路由，渲染指定view
  router.route('/result', function () {
    layout.showIn(layoutSelector,resultV);
  });

    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'resultV': resultV
    });

}));
