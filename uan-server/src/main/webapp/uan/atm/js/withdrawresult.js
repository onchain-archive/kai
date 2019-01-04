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
 *  withdrawresult.js
 *  功能    取款-查看结果视图
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /withdrawresult
 *  前一路由 /withdrawcheckinfo
 *  后一路由 /uanindex
 *  输出变量 window.app.withdrawresultV
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
      var withdrawResultDomTemplate = '<a class="atm-btn btn-right btn-row-4" data-bind="click: returnToIndex">确认/OK</a>\
      <div class="container-fluid">\
        <div class="title">\
          <div class="zh-CN">请提取现金</div>\
          <div class="en-US">Pleace collect the cash.</div>\
        </div>\
        <div class="amt-checkinfo-bar form-horizontal">\
  \
          <div class="form-group">\
            <div class="col-xs-5 info-key">\
              <div class="zh-CN">授权人姓名</div><div class="en-US">Authorizer Name</div>\
            </div>\
            <div class="col-xs-7 info-value"><div class="info-value" data-bind="text:masterName"></div></div>\
          </div>\
  \
          <div class="form-group">\
            <div class="col-xs-5 info-key">\
              <div class="zh-CN">授权人账号</div><div class="en-US">Authorizer Account</div>\
            </div>\
            <div class="col-xs-7 info-value"><div class="info-value" data-bind="text:cardNum"></div></div>\
          </div>\
        </div>\
  \
        <div class="text">\
        <img src="image/cash-out.png">\
        </div>\
      </div>';
  
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var withdrawResultDomString = kendo.template(withdrawResultDomTemplate)({});
  
    // withdrawResult视图view modal
    var withdrawResultVM = kendo.observable({
      cardNum: '',
      info: '',
      masterName: '',
      // init: function () {
      //   console.log("view init", this.customerName);
      // },
      show: function () {
        var ciVM = app.withdrawCheckInfoV.model;
        // 判断是否已收到信息，否则跳转至/
        if(!ciVM.get('masterName')) {
          // 跳转路由至/
          app.redirectTo();
        }

        this.set('cardNum', ciVM.get('cardNum'));
        this.set('info', ciVM.get('info'));
        this.set('masterName', ciVM.get('masterName'));
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
  
    // withdrawResult视图view，从字符串变量withdrawResultDomString中加载DOM结构，绑定ViewModel
    var withdrawResultV = new kendo.View(withdrawResultDomString, {
      model: withdrawResultVM, 
      // init: withdrawResultVM.init.bind(withdrawResultVM),
      show: withdrawResultVM.show.bind(withdrawResultVM),
      hide: withdrawResultVM.hide.bind(withdrawResultVM),
      returnToIndex: withdrawResultVM.returnToIndex.bind(withdrawResultVM)
    });
  
    // withdrawResult视图路由，渲染指定view
    router.route('/withdrawresult', function () {
      layout.showIn(layoutSelector,withdrawResultV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'withdrawResultV': withdrawResultV
    });
  
  }));
  