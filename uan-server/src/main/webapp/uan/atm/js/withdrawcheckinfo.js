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
 *  withdrawcheckinfo.js
 *  功能    取款-确认金额视图
 *  作者    LIU Jiajie
 *  日期    2018-09-xx
 *  当前路由 /withdrawcheckinfo
 *  前一路由 /
 *  后一路由 /withdrawresult
 *  输出变量  window.app.withdrawCheckInfoV
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
    var withdrawCheckInfoDomTemplate = '<a class="atm-btn btn-right btn-row-4" data-bind="click: prevPage">取消/Cancle</a>\
    <a class="atm-btn btn-right btn-confirm btn-row-3" data-bind="click: withdraw">确认/Confirm</a>\
    <div class="container-fluid">\
\
      <div class="title">\
        <div class="zh-CN">请核对姓名和身份证号码</div>\
        <div class="en-US">Pleace check your name and idcard number.</div>\
      </div>\
      <div class="amt-checkinfo-bar form-horizontal">\
\
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</div><div class="en-US">Full Name</div>\
          </div>\
          <div class="col-xs-7"><div class="info-value" data-bind="text:name"></div></div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">身份证号</div><div class="en-US">IdCard No.</div>\
          </div>\
          <div class="col-xs-7"><div class="info-value" data-bind="text:userId"></div></div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">取出金额</div><div class="en-US">Cash Amount</div>\
          </div>\
          <div class="col-xs-7">\
            <div class="info-value">\
            CNY <span data-bind="text:amt"></span>.00\
            </div>\
          </div>\
        </div>\
      </div>\
      <div class="text">\
        <div class="zh-CN">请核对上述信息是否正确，按<b>[确认]</b>键继续</div>\
        <div class="en-US">Please check if the above information is correct,<br> then press <b>[Confirm]</b> to continue.</div>\
      </div>\
    </div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var withdrawCheckInfoDomString = kendo.template(withdrawCheckInfoDomTemplate)({});
  
  
    // withdrawCheckInfo视图view modal
    var withdrawCheckInfoVM = kendo.observable({
      // request
      appId: '',
      amt: '',
      deviceNum: 'ATM模拟器',
      name: '',
      userId: '',
      party: '',
      // respond
      cardNum: '',
      info: '',
      masterName: '',
      // init: function () {
      // },
      show: function () {
        var idVM     = app.idcardloginV.model,
          withdrawVM = app.withdrawV.model;
        // 判断是否已登录
        if(!idVM.get('name')) {
          // 跳转路由至 /
          app.redirectTo();
        } else if(!withdrawVM.get('amt')) {
          // 跳转路由至 /withdraw
          app.redirectTo('/withdraw');
        }
        this.set('appId', idVM.get('appId'));
        this.set('amt', withdrawVM.get('amt'));
        this.set('name', idVM.get('name'));
        this.set('userId', idVM.get('userId'));
        this.set('party', idVM.get('userId')); // TODO - 设置party为userId
      },
      hide: function () {
        this.set('appId', '');
        this.set('amt', '');
        this.set('name', '');
        this.set('userId', '');
        this.set('party', ''); // TODO - 设置party为userId
      },
      prevPage: function() {
        // 跳转路由至 //withdraw
        router.navigate('/withdraw');
      },
      // cancle: function() {
      // },
      withdraw: function() {
        var _this = this, config = app.proxy.withdraw;
        udesk.ajax({
          type: config.type,
          url: config.url, // TODO- 待修改为后端登录URL
          contentType: config.contentType,
          data: JSON.stringify({
            appId: _this.get('appId'),
            data: {
              amt: _this.get('amt'),
              deviceNum: _this.get('deviceNum'),
              party: _this.get('party')
            },
            userId: _this.get('userId')
          })
        })
        .done(function(res) {
          if(res.statusCode != '200') { 
            // TODO - 设置promise为false
            console.error(res.message);
            return;
          }
          // 取款成功，将返回的用户数据写入app.withdrawCheckInfoV.model
          var data = res.data, ciVM = app.withdrawCheckInfoV.model;
          ciVM.set('cardNum', data.cardNum);
          ciVM.set('info', data.info);
          ciVM.set('masterName', data.masterName);
          // 跳转路由至 /withdrawResult
          router.navigate('/withdrawresult');
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          // TODO - 提交返回失败
        });
      },

    });
  
    // withdrawCheckInfo视图view，从字符串变量withdrawCheckInfoDomString中加载DOM结构，绑定ViewModel
    var withdrawCheckInfoV = new kendo.View(withdrawCheckInfoDomString, {
      model: withdrawCheckInfoVM,
      // init: withdrawCheckInfoVM.init.bind(withdrawCheckInfoVM),
      show: withdrawCheckInfoVM.show.bind(withdrawCheckInfoVM),
      hide: withdrawCheckInfoVM.hide.bind(withdrawCheckInfoVM),
      withdraw: withdrawCheckInfoVM.withdraw.bind(withdrawCheckInfoVM),
      // cancle: withdrawCheckInfoVM.cancle.bind(withdrawCheckInfoVM),
      prevPage: withdrawCheckInfoVM.prevPage.bind(withdrawCheckInfoVM),
    });
  
    // withdrawCheckInfo视图路由，渲染指定view
    router.route('/withdrawcheckinfo', function () {
      layout.showIn(layoutSelector, withdrawCheckInfoV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'withdrawCheckInfoV': withdrawCheckInfoV
    });
  }));