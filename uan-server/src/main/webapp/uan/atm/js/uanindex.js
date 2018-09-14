/**
 *  uanindex.js
 *  功能       UAN主页视图
 *  作者       LIU Jiajie
 *  日期       2018-09-xx
 *  当前路由 /uanindex
 *  前一路由 /faceidlogin
 *  后一路由 /withdraw、/findmasters
 *  输出变量  window.app.uanindexV
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
    var uanindexDomTemplate = '<a class="atm-btn btn-left btn-bg btn-row-2" data-bind="click: reportLost">挂失授权人账户</a>\
      <a class="atm-btn btn-left btn-bg btn-row-4" data-bind="click: prevPage">退&nbsp;&nbsp;出</a>\
      <a class="atm-btn btn-right btn-bg btn-row-2" data-bind="click: queryRelativeList">查询授权人账户</a>\
      <a class="atm-btn btn-right btn-bg btn-row-4" data-bind="click: withdraw">小额免密取款</a>\
      <div class="container-fluid" style="height: 100%;">\
        <h1 style="margin: 21px auto; width: 340px;">中国农业银行ATM</h1>\
        <br/><br/><br/>\
        <div style="width: 460px; margin: 36px auto;"><span style="font-size: 26px;">\
          欢迎您，<b data-bind="text: name"></b>\
        </div>\
        <div style="width: 460px; margin: 36px auto;">\
          <span style="font-size: 26px;">UAN为您提供应急服务，请选择业务</span>\
        </div>\
      </div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var uanindexDomString = kendo.template(uanindexDomTemplate) ({});
  
    // uanindex视图view modal
    var uanindexVM = kendo.observable({
      name: '',
      init: function () {
      },
      show: function () {
        this.set('name', app.idcardloginV.model.name);
        // 判断是否已登录
        if(!this.get('name')) {
          // 跳转路由至 /
          app.redirectTo();
        }
      },
      // hide: function () {
      // },
      queryRelativeList: function() {
        // 跳转路由至 /login
        router.navigate('/queryrelativelist');
      },
      reportLost: function() {
        // 跳转路由至 /login
        router.navigate('/reportlosslist');
      },
      withdraw: function() {
        // 跳转路由至 /
        router.navigate('/withdraw');
      },
      prevPage: function() {
        // 跳转路由至 /
        router.navigate('/');
      },
    });
  
    // uanindex视图view，从字符串变量uanindexDomString中加载DOM结构，绑定ViewModel
    var uanindexV = new kendo.View(uanindexDomString, {
      model: uanindexVM,
      // init: uanindexVM.init.bind(uanindexVM),
      show: uanindexVM.show.bind(uanindexVM),
      // hide: uanindexVM.hide.bind(uanindexVM),
      queryRelativeList: uanindexVM.queryRelativeList.bind(uanindexVM),
      reportLost: uanindexVM.reportLost.bind(uanindexVM),
      withdraw: uanindexVM.withdraw.bind(uanindexVM),
      prevPage: uanindexVM.prevPage.bind(uanindexVM),
    });
  
    // UAN主页视图路由，渲染指定view
    router.route('/uanindex', function () {
      layout.showIn(layoutSelector, uanindexV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'uanindexV': uanindexV
    });
  }));