# MoneyTextView

A simple Android TextView to display amounts of money

Support for Android SDK 15 and up.

Example 1 | Example 2
---- | ----
![Example1](/art/screenshot1.png) | ![Example2](/art/screenshot2.png)


## Setup

Puedes agregar esta librería vía gradle de esta manera.

~~~groovy
dependencies {
  compile 'org.fabiomsr:moneytextview:1.0.0'
}  
~~~

## Usage

Add MoneyTextView to you layout

~~~xml                                            

<org.fabiomsr.moneytextview.MoneyTextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       app:symbol="¥"
       app:amount="1256.56"
       />

~~~

#### Result

You can also add more custom options

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

#### Result


### In you code

~~~java

moneyTextView.setAmount(156);

~~~


## Attributes

MoneyTextView ofrece muchas opciones que puedes configurar, en esta tabla puedes ver
cada una con su valor por defecto.

|           Name          |                                                             Description                                                             |        Values        |   Default  |
|:-----------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:--------------------:|:----------:|
| format                  | String conteniendo un formatdo valido de DecimalFormat https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html       | string               | ###,##0.00 |
| amount                  | Cantidad a mostrar                                                                                                                  | float                | 0          |
| baseTextSize            | Tamaño del texto para toda la cantidad sino se especifica el symbolTextSize y/o el decimalDigitsTextSize                            | sp                   | 18sp       |
| baseTextColor           | Color para toda la cantidad sino se especifica el symbolTextColor y/o el decimalTextColor                                           | color                | #000000    |
| gravity                 | Posición relativa del texto dentro de la vista                                                                                      | top,bottom,center... | center     |
| symbol                  | Currency Symbol                                                                                                                     | string               | $          |
| symbolMargin            | Separación entre el simbolo y la cantidad                                                                                           | dp                   | 2dp        |
| symbolTextSize          | Tamaño del texto del simbolo                                                                                                        | sp                   | 18sp       |
| symbolGravity           | Gravedad del simbolo, puede ser delante o dretras de la cantidad (start, end) y alineado arriba o abajo a la cantidad (top, bottom) | start,end,top,bottom | top,start  |
| symbolTextColor         | Color del simbolo                                                                                                                   | color                | #000000    |
| decimalSeparator        | Caracter para separar los decimales                                                                                                 | string               | '          |
| decimalMargin           | Separación entre la parte decimal y la parte entera                                                                                 | dp                   | 2dp        |
| decimalDigitsTextSize   | Tamaño del texto de la parte decimal                                                                                                | sp                   | 18sp       |
| decimalGravity          | La parte decimal puede estar alineada con la parte entera por arriba o por abajo                                                    | top,bottom           | top        |
| decimalTextColor        | Color de la parte decimal                                                                                                           | color                | #000000    |
| decimalUnderline        | Habilitar el subrayar la parte decimal                                                                                              | boolean              | false      |
| includeDecimalSeparator | Ocultar/Mostrar el separador decimal                                                                                                | boolean              | true       |
| fontPath                | Ruta para una fuente propia                                                                                                         | string               |            |



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
