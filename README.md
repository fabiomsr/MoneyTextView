[ ![Download](https://api.bintray.com/packages/fabiomsr/maven/MoneyTextView/images/download.svg) ](https://bintray.com/fabiomsr/maven/MoneyTextView/_latestVersion)
<a href="http://www.methodscount.com/?lib=org.fabiomsr%3Amoneytextview%3A%2B"><img src="https://img.shields.io/badge/Methods and size-87 | 10 KB-e91e63.svg"/></a>
<a href="http://twitter.com/xenss"><img src="https://img.shields.io/badge/Twitter-@xenss-blue.svg" alt="Twitter" data-canonical-src="https://img.shields.io/badge/Twitter-@xenss-blue.svg" style="max-width:100%;"></a>
<a href="http://twitter.com/develodroid"><img src="https://img.shields.io/badge/Twitter-@develodroid-blue.svg" alt="Twitter" data-canonical-src="https://img.shields.io/badge/Twitter-@develodroid-blue.svg" style="max-width:100%;"></a>


# MoneyTextView

A custom Android TextView to display amounts of money in different formats.

Minimum Android SDK supported: 15

Example 1 | Example 2
---- | ----
![Example1](https://raw.githubusercontent.com/fabiomsr/MoneyTextView/develop/art/screenshot1.png) | ![Example2](https://raw.githubusercontent.com/fabiomsr/MoneyTextView/develop/art/screenshot2.png)


## Setup

Provide the gradle dependency:

~~~groovy
dependencies {
  compile 'org.fabiomsr:moneytextview:1.0.0'
}  
~~~

## Usage

* Include MoneyTextView in a layout xml file:

~~~xml                                            

<org.fabiomsr.moneytextview.MoneyTextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       app:symbol="¥"
       app:amount="1256.56"
       />

~~~

* Include the following code in your Activity in order to update the amount value:

~~~java

moneyTextView.setAmount(156);

~~~

#### More settings example

~~~xml
<org.fabiomsr.moneytextview.MoneyTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="32dp"
        app:symbol="$"
        app:symbolGravity="start|top"
        app:symbolTextSize="30sp"
        app:symbolMargin="6dp"
        app:amount="1256.56"
        app:baseTextSize="54sp"
        app:decimalDigitsTextSize="30sp"
        app:decimalMargin="6dp"
        app:includeDecimalSeparator="false"
        app:baseTextColor="#FBFFE3"/>
~~~

## Attributes

Money text view offers several attributes for a deeper view configuration, the following table shows all these options and their default value.

|           Name          |                                                             Description                                                             |        Values        |   Default  |
|:-----------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------:|:----------:|
| format                  | String containing a DecimalFormat valid format [DecimalFormat]       | string               | ###,##0.00 |
| amount                  | Amount of money to be displayed                                                                                                                  | float                | 0          |
| baseTextSize            | Text size, if neither of decimalDigitsTextSize or symbolTextSize are specified this attribute will effect the whole text                            | sp                   | 18sp       |
| baseTextColor           | Text size, if neither of decimalTextColor or symbolTextColor are specified this attribute will effect the whole text                                          | color                | #000000    |
| gravity                 | Text relative position inside the view                                                                                      | top,bottom,center... | center     |
| symbol                  | Currency Symbol                                                                                                                     | string               | $          |
| symbolMargin            | Separation between the currency symbol and the amount                                                                                           | dp                   | 2dp        |
| symbolTextSize          | Currency symbol text size                                                                                                       | sp                   | 18sp       |
| symbolGravity           | Currency symbol gravity attribute | start,end,top,bottom | top,start  |
| symbolTextColor         | Currency symbol Color                                                                                                                   | color                | #000000    |
| decimalSeparator        | Decimal part separator character                                                                                                | string               | '          |
| decimalMargin           | Separator between the integer part and the decimal                                                                                 | dp                   | 2dp        |
| decimalDigitsTextSize   | Decimal part text size                                                                                               | sp                   | 18sp       |
| decimalGravity          | Decimal part gravity attribute                                                    | top,bottom           | top        |
| decimalTextColor        | Decimal part color                                                                                                           | color                | #000000    |
| decimalUnderline        | Enables decimal part underlining                                                                                              | boolean              | false      |
| includeDecimalSeparator | Hides/Shows the decimal part separator                                                                                                | boolean              | true       |
| fontPath                | Path to a custom font                                                                                                         | string               |            |




License
-------

    Copyright 2016 Fabio Santana (fabiomsr)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[DecimalFormat]:https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
