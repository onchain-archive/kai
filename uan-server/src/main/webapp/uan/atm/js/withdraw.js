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
 *  withdraw.js
 *  功能       小额免密取款视图
 *  作者       LIU Jiajie
 *  日期       2018-09-xx
 *  当前路由 /withdraw
 *  前一路由 /
 *  后一路由 /withdrawcheckinfo
 *  输出变量  window.app.withdrawV
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
    var withdrawDomTemplate = '<a class="atm-btn btn-left btn-row-1" data-bind="click: amt100">100</a>\
    <a class="atm-btn btn-left btn-row-2" data-bind="click: amt200">200</a>\
    <a class="atm-btn btn-left btn-row-3" data-bind="click: amt300">300</a>\
    <a class="atm-btn btn-left btn-row-4" data-bind="click: amt500">500</a>\
    <a class="atm-btn btn-right btn-row-2" data-bind="click: clear">更正/Clear</a>\
    <a class="atm-btn btn-right btn-row-3 btn-confirm" data-bind="click: confirm">确认/OK</a>\
    <a class="atm-btn btn-right btn-row-4" data-bind="click: prevPage">取消/Cancle</a>\
    <div class="container-fluid">\
      <div class="title">\
        <div class="zh-CN">请选择或输入取款金额</div>\
        <div class="en-US">Please select or input the amount of cash you wish to withdraw.</div>\
      </div>\
      <div class="amt-bar">\
        <input type="text" class="form-control" name="amt" autocomplete="off"\
          data-bind="value:amt">\
      </div>\
      <div class="text">\
        <div class="zh-CN">涉及异地或跨行交易可能收费<br>请咨询发卡行</div>\
        <div class="en-US">Please contact the issuing bank for possible charges<br> related to off-site or inter-bank transactions.</div>\
      </div>\
      <div class="text" style="margin-top:60px;">\
        <div class="zh-CN">本机只提供面额  100  元人民币<br>且单笔最多取 500 元</div>\
        <div class="en-US">This machine only provides banknotes of CNY 100, <br>and a maximum of CNY 500 per transaction.</div>\
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