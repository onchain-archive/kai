/**
 *  withdraw.js
 *  功能       小额免密取款视图
 *  作者       LIU Jiajie
 *  日期       2018-09-xx
 *  当前路由 /withdraw
 *  前一路由 /
 *  后一路由 /withdrawcheckinfo
 *  输出变量  window.app.withdrawV
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
    var withdrawDomTemplate = '<a class="atm-btn btn-left btn-row-1" data-bind="click: amt100">100</a>\
    <a class="atm-btn btn-left btn-row-2" data-bind="click: amt200">200</a>\
    <a class="atm-btn btn-left btn-row-3" data-bind="click: amt300">300</a>\
    <a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">返&nbsp;&nbsp;回</a>\
    <a class="atm-btn btn-right btn-row-1" data-bind="click: amt500">500</a>\
    <a class="atm-btn btn-right btn-row-3" data-bind="click: clear">更&nbsp;&nbsp;正</a>\
    <a class="atm-btn btn-right btn-row-4 btn-confirm" data-bind="click: confirm">确&nbsp;&nbsp;认</a>\
    <div class="container-fluid">\
      <h1 style="margin: 21px auto; width: 340px;">请输入取款金额</h1>\
      <br/><br/><br/>\
      <div class="amt-bar">\
        <input type="text" class="form-control" name="amt" autocomplete="off"\
          data-bind="value:amt">\
      </div>\
    </div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var withdrawDomString = kendo.template(withdrawDomTemplate) ({});
  
  
    // withdraw视图view modal
    var withdrawVM = kendo.observable({
      // name: '',
      // userId: '',
      amt: '',
      init: function () {
        // console.log('view init', this.customerName);
      },
      show: function () {
        // 判断是否已登录，否则跳转至/
        if(!app.idcardloginV.model.name) {
          // 跳转路由至/
          app.redirectTo();
        }
        this.set('amt', '');
      },
      // hide: function () {
      //   console.log('view hide', this.customerName);
      // },
      amt100:  function() {
        this.set('amt', 100);
      },
      amt200:  function() {
        this.set('amt', 200);
      },
      amt300:  function() {
        this.set('amt', 300);
      },
      amt500:  function() {
        this.set('amt', 500);
      },
      confirm: function() {
        if(this.amt) {
          // 跳转路由至 /login
          router.navigate('/withdrawcheckinfo');
        }
      },
      clear: function() {
        this.set('amt', undefined);
      },
      prevPage: function() {
        // 跳转路由至 /
        router.navigate('/uanindex');
      },
    });
  
    // withdraw视图view，从字符串变量withdrawDomString中加载DOM结构，绑定ViewModel
    var withdrawV = new kendo.View(withdrawDomString, {
      model: withdrawVM,
      // init: withdrawVM.init.bind(withdrawVM),
      show: withdrawVM.show.bind(withdrawVM),
      // hide: withdrawVM.hide.bind(withdrawVM),
      amt100: withdrawVM.amt100.bind(withdrawVM),
      amt200: withdrawVM.amt200.bind(withdrawVM),
      amt300: withdrawVM.amt300.bind(withdrawVM),
      amt500: withdrawVM.amt500.bind(withdrawVM),
      confirm: withdrawVM.confirm.bind(withdrawVM),
      clear: withdrawVM.clear.bind(withdrawVM),
      prevPage: withdrawVM.prevPage.bind(withdrawVM),
    });
  
    // withdraw视图路由，渲染指定view
    router.route('/withdraw', function () {
      layout.showIn(layoutSelector, withdrawV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'withdrawV': withdrawV
    });
  }));