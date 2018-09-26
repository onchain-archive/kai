/**
 *  agreement.js
 *  功能       签阅条款视图
 *  作者       xxx
 *  日期       2018-08-xx
 *  当前路由 /agreement
 *  前一路由 /login
 *  后一路由 /addaccount
 *  输出变量 window.app.agreementV
 * 
 * Copyright Agricultural Bank of China.
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
  var domTemplate = '<header>\
  <div class="prev-btn" data-bind="click: prevPage"><i class="fa fa-chevron-left"></i></div>\
  <div class="header-title">Terms of Use</div>\
</header>\
\
  <div class="container-fluid with-bottom with-checkbox"  style="padding-top: 15px;"> \
<div class="article-horizontal"> \
  <p> \
    Welcome to UAN Emergency Service! Your information is as follows:<br>\
<br>\
Full Name：<span data-bind="text: name"></span><br>\
ID Card No.：<span data-bind="text: idCard"></span><br>\
Phone：<span data-bind="text: phone"></span><br>\
<br>\
PLEASE READ THESE TERMS AND CONDITIONS CAREFULLY. BY ACCEPTING THIS PAGE YOU AGREE TO BE BOUND BY THE TERMS AND CONDITIONS BELOW. THESE TERMS AND CONDITIONS ARE SUBJECT TO CHANGE. ANY CHANGES WILL BE INCORPORATED INTO THE TERMS AND CONDITIONS POSTED TO THIS PAGE FROM TIME TO TIME. IF YOU DO NOT AGREE WITH THESE TERMS AND CONDITIONS, PLEASE DO NOT ACCESS THIS PAGE.<br>\
<br>\
Unauthorized use of Agricultural Bank of China\'s APPs, Websites and systems, including but not limited to unauthorized entry into Agricultural Bank of China\'s systems, misuse of passwords, posting of objectionable or offensive content or your unauthorized use of legally protected third party content, or misuse of any information posted to a site, is strictly prohibited.<br>\
<br>\
You acknowledge that Agricultural Bank of China may disclose and transfer any information that you provide through this Page to (i) any company within the Agricultural Bank of China, its affiliates agents or information providers; (ii) to any other person or entity with your consent; or (iii) if we have a right or duty to disclose or are permitted or compelled to so disclose such information by law. You consent to the transmission, transfer or processing of such information to, or through, any country in the world, as we deem necessary or appropriate (including to countries outside the EEA), and by using and providing information through this Page you agree to such transfers. Use of this Page, including any patterns or characteristics concerning your interaction with it, may be monitored, tracked and recorded. Anyone using this Page expressly consents to such monitoring, tracking and recording.<br>\
<br>\
You agree not to attempt to log on to the Page from any country under sanctions by the Office of Foreign Assets Control (OFAC). Information regarding which countries are under sanctions may be obtained on the U.S. Department of the Treasury website. Any attempt to log on to the Page from one of these countries may result in your access being restricted and/or terminated.<br>\
<br>\
If you use Agricultural Bank of China\'s Page or systems to access data related to any account(s) of which you are not the owner or authorized user as reflected in Agricultural Bank of China\'s systems, you shall indemnify, defend, and hold harmless Agricultural Bank of China and all of its direct and indirect subsidiaries, officers, directors, employees, agents, successors, and assigns from any and all losses, liabilities, damages, and all related costs and expenses, arising from, relating to, or resulting (directly or indirectly) from such access. Further, without limiting Agricultural Bank of China\'s rights or your obligations under any other provision of these Terms and Conditions, and notwithstanding the same, in the event of any actual or reasonably suspected unauthorized access to the personal information of a customer (including but not limited to customer names, addresses, phone numbers, bank and credit card account numbers, income and credit histories, and social security numbers) under your control or subsequent to and arising from your past exercise of control, direct damages in connection with any such breach will include the cost and expenses of investigation and analysis (including by law firms and forensic firms), correction or restoration of any destroyed, lost or altered data, notification to affected customers, offering and providing of credit monitoring, customers service, or other remediation services, and any related cost. Agricultural Bank of China\’s rights to indemnity under this section are in addition to all other rights and remedies available at Law or in equity. Any exercise by Agricultural Bank of China of its rights to indemnification shall be without prejudice to such other rights and remedies. You manifest your assent to this indemnity by accessing account data through Agricultural Bank of China\'s Website or systems, notwithstanding the terms of any agreement you have with a customer or an account owner stating otherwise. This indemnity includes but is not limited to losses associated with (1) a data breach of your system(s) and (2) a data breach of the system(s) of any person or entity with whom you provided or shared Agricultural Bank of China customer account data.<br>\
<br>\
</p>\
</div>\
  <div class="checkbox-bar">\
  <input type="checkbox" name="isAgree" id="isAgreeCheckbox" data-bind="checked: agreeWithUan" autocomplete="off">\
  <label for="isAgreeCheckbox" style="float: initial;">I\'ve read through the Terms of Use above.</label>\
  </div>\
</div>\
<div class="btn-bar fix-bottom">\
<button class="btn btn-default" data-bind="click: prevPage">I don\'t agree.</button>\
<button class="btn btn-theme pull-right" data-bind="click: nextPage,  enabled: agreeWithUan">I agree.</button>\
</div>';

  // 使用template构造DOM字符串，传入从上一视图读取的变量
//  var domString = kendo.template(domTemplate)({
//  });
  var domString = domTemplate;

  // agreement视图view modal
  var agreementVM = kendo.observable({
    // 以下为默认值，待覆盖为loginV.model中的值
    appId: '',
    userId: '',
    password: '',
    id: '',
    idCard: '',
    name: '',
    phone: '',
    state: '',
    type: '',
    agreeWithUan: false,
    init: function () {
    //  var data = window.app.loginV.model;

    //  this.set('appId', data.appId);
    //  this.set('userId', data.userId);
    //  this.set('password', data.password);
    //  this.set('id', data.id);
    //  this.set('idCard', data.idCard);
    //  this.set('name', data.name);
    //  this.set('phone', data.phone);
    //  this.set('state', data.state);
    //  this.set('type', data.type);

    if(jQuery.fn.niceScroll) {
      $('.container-fluid').last().niceScroll({cursorcolor:'#7f7f7f'});
    }

    },
    show: function () {
      this.updateData();
      if(!this.get('name')) {
        console.error('No login information!');
        // route to /login
        app.redirectTo('/login');
        return;
      }
    },
    // hide: function () {
    // },
    updateData: function() {
      var data = window.app.loginV.model;
      this.set('appId', data.appId);
      this.set('userId', data.userId);
      this.set('password', data.password);
      this.set('id', data.id);
      this.set('idCard', data.idCard);
      this.set('name', data.name);
      this.set('phone', data.phone);
      this.set('state', data.state);
      this.set('type', data.type);
    },
    prevPage: function () {
      router.navigate('/login');
    },
    nextPage: function () {
      if(this.agreeWithUan) {
        router.navigate("/addaccount");
      }
    },

  });

  // agreement视图view，从字符串变量domString中加载DOM结构，绑定ViewModel
  var agreementV = new kendo.View(domString, {
    wrap: false,
    model: agreementVM, 
    init: agreementVM.init.bind(agreementVM),
    show: agreementVM.show.bind(agreementVM),
    // hide: agreementVM.hide.bind(agreementVM),
    prevPage: agreementVM.prevPage.bind(agreementVM),
    nextPage: agreementVM.nextPage.bind(agreementVM),
  });

  // agreement视图路由，渲染指定view
  router.route("/agreement", function () {
    layout.showIn(layoutSelector, agreementV, 'swap');
  });


  // 输出视图至全局变量app
  window.app = $.extend(true, app, {
    'agreementV': agreementV
  });
}));
