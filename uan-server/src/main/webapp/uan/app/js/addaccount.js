/**
 *  addaccount.js
 *  功能       添加账户视图
 *  作者       xxx
 *  日期       2018-08-xx
 *  当前路由 /addaccount
 *  前一路由 /agreement
 *  后一路由 /addrelative
 *  输出变量 window.app.addaccountV
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

    // 修改maskedtextbox以支持MVVM模式
  //   kendo.data.binders.widget.mask = kendo.data.Binder.extend({
  //     init: function (widget, bindings, options) {
  //         //call the base constructor
  //         kendo.data.Binder.fn.init.call(this, widget.element[0], bindings, options);

  //         this.widget = widget;
  //     },
  //     refresh: function () {
  //         var value = this.bindings.mask.get();

  //         this.widget.setOptions({ mask: value });
  //     }
  // });

    //构造单个签约账户Form DOM模板
    var accountFormDomTemplate = '<form class="form-horizontal" data-bind="attr: {data-index: index}">\
    <a class="fa fa-remove form-remove" data-bind="click: removeAccount"></a>\
    <div class="form-group">\
      <div class="col-xs-3">\
        <label><i style="color:red;">*</i>姓&nbsp;&nbsp;&nbsp;名</label>\
      </div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="name" placeholder="请输入姓名"\
          data-bind="value: customerInformation.name" disabled="disabled" autocomplete="off"\
          required validationMessage="请输入姓名!">\
      </div>\
    </div>\
    \
    <div class="form-group">\
      <div class="col-xs-3">\
        <label><i style="color:red;">*</i>身份证</label>\
      </div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="idCard" placeholder="请输入身份证"\
          data-bind="value:customerInformation.idCard" disabled="disabled" autocomplete="off"\
          required pattern="[0-9Xx]{18}" validationMessage="请输入正确格式的身份证!">\
      </div>\
    </div>\
    \
    <div class="form-group">\
      <div class="col-xs-3">\
        <label><i style="color:red;">*</i>账&nbsp;&nbsp;&nbsp;号</label>\
      </div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="code" placeholder="请输入账号"\
          data-bind="value:code, disabled: disabled" autocomplete="off"\
          required pattern="[0-9]{16,19}" validationMessage="请输入正确格式的账号!">\
      </div>\
    </div>\
    \
    \
    <div class="form-group">\
        <div class="col-xs-3">\
          <label><i style="color:red;">*</i>开户行</label>\
        </div>\
        <div class="col-xs-8">\
          <input name="bankOfDeposit" autocomplete="off"\
            data-role="dropdownlist"\
            data-value-primitive="true"\
            data-text-field="name"\
            data-value-field="id"\
            data-bind="value: bankOfDeposit, source: products, disabled: disabled"\
            style="width: 100%;"\
            required validationMessage="请选择开户行!"\
          />\
          <!-- 加入此span以解决验证提示框显示位置问题，data-for属性必须与input的name属性一致 -->\
          <span class="k-invalid-msg" data-for="bankOfDeposit"></span>\
    \
      </div>\
    </div>\
    </form>';


  // 构造视图DOM模板
  var addaccountDomTemplate = '<div class="container-fluid" style="max-height: 90%;"> \
  <h1>请添加授权账户</h1>\
  \
  <div class="add-bar add-account">\
    <button class="btn btn-theme btn-add" data-bind="click: addAccount">添加新账户</button>\
  </div><br><br><br>\
\
</div>\
\
<div class="btn-bar fix-bottom">\
  <button class="btn btn-default" data-bind="click: prevPage">上一步</button>\
  <button class="btn btn-theme pull-right" data-bind="click: nextPage">下一步</button>\
</div>';

  // 使用template构造单个Form DOM字符串
  var accountFormDomString = kendo.template(accountFormDomTemplate)({});

  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var addaccountDomString = kendo.template(addaccountDomTemplate)({
    // 'accountForm': accountFormDomString
   });


  // addaccount视图view modal
  var addaccountVM = kendo.observable({
    formViews: {},
    formViewsIndex: 0, // 只增不减
    init: function () {
      // 初始化时加载用户卡数据
      var userData = window.app.loginV.model;
      var config = window.app.proxy.addaccount;
      udesk.ajax({
        type: config.type,
        url: config.url, // TODO - 待修改为后端findMyCard URL
        contentType: config.contentType,
        data: JSON.stringify({
          appId: userData.appId,
          userId: userData.userId
        }),
      })
      .done(function(res) {
        var data = res.data, i, defaultName = data[0].customerInformation.name;
        // 遍历并展示返回的卡信息
        for(i=0; i<data.length; i++) {
          window.app.addaccountV.model.addAccount($.extend(true,{},{
            customerInformation: {
              name: defaultName
            }
          },data[i]), true);
        }
      })
      .fail(function(jqXHR, textStatus, errorThrown) {
        console.error(textStatus, ': ', errorThrown);
      });

      if(jQuery.fn.niceScroll) {
        $('.container-fluid').last().niceScroll({cursorcolor:'#7f7f7f'});
      }
    },

    prevPage: function () {
      router.navigate('/agreement');
    },
    nextPage: function () {
      if(this.validateAccounts(this.get('formViews'))) {
        // TODO - 若所有Form校验成功，则跳转
        router.navigate('/addrelative');
      } else {
        udesk.messageBox({
          // title: '校验失败',
          // icon: 'fa ',
          message: '表单校验失败，请完善个人账户信息！'
        });
      }

      return;
    },
    getCurrentViewsNumber: function() {
      var i, n=0;
      for(i=0; i<this.get('formViewsIndex'); i++) {
        if(this.get('formViews')[i]) {n++;}
      }
      return n;
    },
    getCurrentFirstView: function() {
      var i, n=0;
      for(i=0; i<this.get('formViewsIndex'); i++) {
        if(this.get('formViews')[i]) {return this.get('formViews')[i];}
      }
      return null;
    },
    addAccount: function(card, disabled) {
      var _this = this, defaultV = _this.getCurrentFirstView(), defaultVM;
      card.customerInformation = card.customerInformation || {};
      var defaultIdCard = '', defaultName = '';

      if(defaultV && (defaultVM = defaultV.model)) {
        defaultIdCard = defaultVM.idCard;
        defaultName = defaultVM.customerInformation.name;
      }

      var newFormVM = kendo.observable({
        index: _this.get('formViewsIndex'), // 此处this为addaccountVM实例
        amt: card.amt,
        bankOfDeposit: card.bankOfDeposit,
        code: card.code,
        customerInformation: {
          id: card.customerInformation.id || card.id ||'',
          idCard: card.customerInformation.idCard || card.idCard || defaultIdCard,
          name: card.customerInformation.name || defaultName,
          phone: card.customerInformation.phone || '',
          state: card.customerInformation.state || '',
          type: card.customerInformation.type || '',
        },
        id: card.id || card.customerInformation.id || '',
        idCard: card.idCard || card.customerInformation.idCard || defaultIdCard,
        state: card.state,
        // idCardMask: '000000000000000000',
        // codeMask: '0000 0000 0000 0000 000',
        disabled: disabled || false,
        validator: null,
        removeAccount: function() {
          if(_this.getCurrentViewsNumber() < 1) {
            return;
          }

          _this.get('formViews')[this.get('index')].destroy();// 此处this为newFormVM实例
          delete _this.get('formViews')[this.get('index')];
        }, // END OF removeAccount
        // 下拉菜单相关数据
        products: [
          { id: 'abc', name: '中国农业银行' },
          { id: 'ccb', name: '中国建设银行' },
          { id: 'icbc', name: '中国工商银行' },
          { id: 'boc', name: '中国银行' }
        ],
       }); // END OF newFormVM

      var newFormV = new kendo.View(accountFormDomString, {
        model: newFormVM,
        removeAccount: newFormVM.removeAccount.bind(newFormVM),
      }); // END OF newFormV

      // 动态添加View
      var $newForm = $('.add-account').before(newFormV.render()).prev();

      newFormV.model.validator = $newForm.kendoValidator({
        // validateOnBlur: false // Disable the default validation on blur
      }).data('kendoValidator');

      // 保存当前view到formViews
      _this.get('formViews')[_this.get('formViewsIndex')] = newFormV;
      _this.set('formViewsIndex', _this.get('formViewsIndex')+1); // 只增不减

    }, // END OF addAccount
    validateAccount: function(accountView) {
      var model;
      if(accountView && (model = accountView.model)) {
        return model.validator.validate();
      }
      return false;
    }, // END OF validateAccount
    validateAccounts: function() {
      var views = this.get('formViews'), number = this.getCurrentViewsNumber(), i;
      if(!views || !number) {
        return false;
      }
      for(i=0; i<this.get('formViewsIndex'); i++) {
        if(views[i] && !this.validateAccount(views[i])) {
          return false;
        }
      }
      return true;
    }, // END OF validateAccounts

  });

  // addaccount视图view，从字符串变量addaccountDomString中加载DOM结构，绑定ViewModel
  var addaccountV = new kendo.View(addaccountDomString, {
    model: addaccountVM, 
    init: addaccountVM.init.bind(addaccountVM),
    // show: addaccountVM.show.bind(addaccountVM),
    // hide: addaccountVM.hide.bind(addaccountVM),
    prevPage: addaccountVM.prevPage.bind(addaccountVM),
    nextPage: addaccountVM.nextPage.bind(addaccountVM),
    addAccount: addaccountVM.addAccount.bind(addaccountVM),
  });

  // addaccount视图路由，渲染指定view
  router.route('/addaccount', function () {
    layout.showIn(layoutSelector, addaccountV);
  });


  // 输出至全局变量app
  window.app = $.extend(true, app, {
    'addaccountV': addaccountV
  });

}));
