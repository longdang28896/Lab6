package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {
    private TextView tvSOAP;
    private EditText edtEnterText;
    String textEnter = "";
    String result = "";


    final String url = "https://www.w3schools.com/xml/tempconvert.asmx";

    final String actionC = "https://www.w3schools.com/xml/CelsiusToFahrenheit";
    final String paramC = "Celsius";

    final String actionF = "https://www.w3schools.com/xml/FahrenheitToCelsius";
    final String paramF = "Fahrenheit";

    final String namspace = "https://www.w3schools.com/xml/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSOAP = (TextView) findViewById(R.id.tvSOAP);
        edtEnterText = (EditText) findViewById(R.id.edtEnterText);

    }

    public void btnCtoF(View view) {
        textEnter = edtEnterText.getText().toString();
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                SoapObject soapObject = new SoapObject(namspace, "CelsiusToFahrenheit");
                soapObject.addProperty(paramC, textEnter);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(soapObject);

                HttpTransportSE httpTransportSE = new HttpTransportSE(url);

                try {
                    httpTransportSE.call(actionC, envelope);
                    SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
                    String ketqua = soapPrimitive.toString();

                    result = soapPrimitive.toString();
                    Toast.makeText(MainActivity.this, ketqua, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                tvSOAP.setText(textEnter + " Celsius = "+result+" Fahrenheit");
            }
        };
        asyncTask.execute();
    }

    public void btnFtoC(View view) {
        textEnter = edtEnterText.getText().toString();
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                SoapObject soapObject = new SoapObject(namspace, "FahrenheitToCelsius");
                soapObject.addProperty(paramF, textEnter);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(soapObject);

                HttpTransportSE httpTransportSE = new HttpTransportSE(url);

                try {
                    httpTransportSE.call(actionF, envelope);
                    SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
                    String ketqua = soapPrimitive.toString();

                    result = soapPrimitive.toString();
                    Toast.makeText(MainActivity.this, ketqua, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                tvSOAP.setText(textEnter + " Fahrenheit = "+result+" Celsius");
            }
        };
        asyncTask.execute();
    }
}