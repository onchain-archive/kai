# United Aid Net
@R&D Center Agricultural Bank Of China 

*IBM : Call for Code*

**United Aid Net (UAN) is a global emergency assistance network for uninterrupted financial services under natural disasters. It allows people to withdraw cash and use other financial services from their own accounts or relatives’ accounts by using FaceID during a disaster.**

*[`Introduction To UAN`](https://github.com/cellchip/uan/blob/master/README.md)*

## Getting Started
****
### Watching Demo videos:
Watching the Demo video of "Customers Sign Agreements and Identify Relatives As Beneficiaries Through the Banking App", please click: 
[**UAN APP Signing**](https://youtu.be/l_qmFfjQLVg)<br>

Watching the Demo video of "Beneficiaries Withdraw Small Amount of Cash Through UAN Alliances’ ATMs", please click: 
[**UAN ATM Withdrawing Cash**](https://youtu.be/jrcCD4hP8Hs)

****

### 1. Customers Sign Agreements and Identify Relatives As Beneficiaries Through the Banking App

#### (1) Logining Account
Entering the following URL in the browser's address bar :
**https://uan-tomcat.mybluemix.net/uan/app/index.html** <br>
Getting the main interface：

<br>
<div align=center><img width="350" height="620" title="UAN App Main UI" src="https://github.com/cellchip/uan/blob/master/resources/app%20main%20ui.png"/></div>
<br>

Click the `UAN Emergency Service` and Get：

<br>
<div align=center><img width="350" height="620" title="UAN App Login" src="https://github.com/cellchip/uan/blob/master/resources/app%20login.png"/></div>
<br>

Entering Account:<br>
* `110102200211112222 ` 
<br>
Click Fingerprint Icon.

#### (2) Agreeing Agreements
Tick the “I agree” and Click Next：

<br>
<div align=center><img width="350" height="620" title="UAN App User Terms" src="https://github.com/cellchip/uan/blob/master/resources/app%20terms.png"/></div>
<br>

#### (3) Binding bank card accounts
* Accounts No: `4217658939208023`
* Bank of Deposit: `Bank-of-America`

<br>
<div align=center><img width="350" height="620" title="UAN App Binding" src="https://github.com/cellchip/uan/blob/master/resources/app%20binding.png"/></div>
<br>

#### (4) Adding Beneficiaries' Information
* Full Name：`Fred Chow`
* ID Card No:`110102201506238888`
* Phone Number:`11 digit phone number`
* Relationship：`Son`
* FaceID：`Tick one photo`

<br>
<div align=center><img width="350" height="620" title="UAN App Add Relatives" src="https://github.com/cellchip/uan/blob/master/resources/app%20add%20relatives.png"/></div>
<br>

#### (5) Checking Information
Confirm & Submit：

<br>
<div align=center><img width="350" height="620" title="UAN App Checking" src="https://github.com/cellchip/uan/blob/master/resources/app%20check.png"/></div>
<br>

Click `Complete`!<br>
**Success!**

<br>
<div align=center><img width="350" height="620" title="UAN App Success" src="https://github.com/cellchip/uan/blob/master/resources/app%20success.png"/></div>
<br>

TIP: If it is not success,you need to initialize data：Entering the following URL in the browser's address bar :
**https://uan-tomcat.mybluemix.net/api/rest/hyp/init** 
Browser returns: 
```
{"appId":"uan","message":"请求成功","statusCode":"200"}
```
And please signing agreement again.

****

### 2. Beneficiaries Withdraw Small Amount of Cash Through UAN Alliances’ ATMs
#### (1) Logining Account
Entering the following URL in the browser's address bar :
**https://uan-tomcat.mybluemix.net/uan/atm/index.html**<br>

<br>
<div align=center><img width="600" height="428" title="UAN ATM Main UI" src="https://github.com/cellchip/uan/blob/master/resources/atm%20main%20ui.png"/></div>
<br>

Entering Account:<br>
* `110102201506238888`
<br>

<br>
<div align=center><img width="600" height="428" title="UAN ATM Login" src="https://github.com/cellchip/uan/blob/master/resources/atm%20login.png"/></div>
<br>

Click `UAN Emergency Service` and Get:

<br>
<div align=center><img width="600" height="428" title="UAN ATM UAN" src="https://github.com/cellchip/uan/blob/master/resources/atm%20uan.png"/></div>
<br>

#### (2) Withdrawing Cash
Click `Secret-free Cash Withdraw`and entering amount from 100 to 500. Then Check information and Click `Confirm` :

<br>
<div align=center><img width="600" height="428" title="UAN ATM Withdrawing" src="https://github.com/cellchip/uan/blob/master/resources/atm%20200.png"/></div>
<br>

<br>
<div align=center><img width="600" height="428" title="UAN ATM Success" src="https://github.com/cellchip/uan/blob/master/resources/atm%20get%20cash.png"/></div>
<br>

Withdrawing successfully!<br>
Then getting the user interface for UAN Emergency Service. Continue or Logout!<br>

### 2. Disaster Happens and Enabling UAN Services
> When a disaster happens, government will launch an official announcement, claim disaster area scope and confirm the affected period. Then it enables UAN services. Financial institutions in UAN provide financial services such as petty cash withdrawals, inquiries, reporting loss and inter-bank clearance. 
#### Management Panel of UNA
Entering the following URL in the browser's address bar :
**https://uan-tomcat.mybluemix.net/uan/web/index.html**<br>
Getting the interface of management. There are three menus which is `Union Menbers` , `Public Accounts` and `Catastrophe Model`.

<br>
<div align=center><img width="600" height="300" title="UAN Web Manage" src="https://github.com/cellchip/uan/blob/master/resources/web%20manage.PNG"/></div>
<br>

**Union Members:** You can manage all UAN members`Add`,`Edit`,`Delete` and `Search`.

<br>
<div align=center><img width="600" height="300" title="UAN Web Accounts" src="https://github.com/cellchip/uan/blob/master/resources/web%20accounts.PNG"/></div>
<br>

**Public Accounts:** You can check all `Transaction details`on this page.

****

[Introduction To UAN](https://github.com/cellchip/uan/blob/master/README.md)
