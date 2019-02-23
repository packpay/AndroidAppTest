package ir.packpay.packpaysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import ir.packpay.iablib.model.ServiceClient;
import ir.packpay.iablib.handler.AsyncCallback;
import ir.packpay.iablib.data.dto.FaultResponse;
import ir.packpay.iablib.data.dto.InternalPurchaseInformationDto;
import ir.packpay.iablib.data.dto.SubscriptionResponse;


public class MainActivity extends AppCompatActivity {

    private static final String REFRESH_TOKEN = "d39e1a63-68c3-472e-ac6e-a6056931ab65";
    private static final String CLIENT_ID = "LoAGzBleII93F6MdQPFr";
    private static final String SECRET_ID = "lws004ulel3FE2OvSPUbqrISGSOAcQ";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.click_me_btn);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = new InternalPurchaseInformationDto()
                        .setRefreshToken(REFRESH_TOKEN)
                        .setClientId(CLIENT_ID)
                        .setClientSecretId(SECRET_ID)
                        .setServiceCode("124")
                        .setProductCode("IN_APP_PURCHASE_124_128");


                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);

                serviceClient.purchase(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling purchase Success");
                        Toast.makeText(getBaseContext(), "خرید با موفقیت انجام شد", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {

                        Log.i(MainActivity.class.getSimpleName(), "calling purchase failed");
//                        Toast.makeText(getBaseContext(), "خرید ناموفق بود", Toast.LENGTH_LONG).show();
                          Toast.makeText(getBaseContext(), faultResponse.getMessage().toString(), Toast.LENGTH_LONG).show();


                    }
                });
            }
        });


        //////////////////////////////

        Button subscribeButton = findViewById(R.id.subscribe_btn);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InternalPurchaseInformationDto dto = new InternalPurchaseInformationDto()
                        .setRefreshToken(REFRESH_TOKEN)
                        .setClientId(CLIENT_ID)
                        .setClientSecretId(SECRET_ID)
                        .setServiceCode("119")
                        .setProductCode("TIME_BASED_DAILY_119_119");

                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.subscribe(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling subscribe Success");

                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling subscribe failed");
                        Toast.makeText(getBaseContext(), faultResponse.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


        Button consumable_purchase_report_btn = findViewById(R.id.consumable_purchase_report_btn);
        consumable_purchase_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = getDto()
                        .setServiceCode("119")
                        .setProductCode("IN_APP_PURCHASE_119_118")
                        .setOperator("BANK")
                        .setUserNumber("09122979012");


                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.consumablePurchaseReport(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling Consumable Purchase Report Success");
                        Toast.makeText(getBaseContext(), "گزارش گرفته شد", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling Consumable Purchase Report Failed");
                        Toast.makeText(getBaseContext(), "گزارش گرفته نشد", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        //////////////////////

        Button subscription_purchase_report_btn = findViewById(R.id.subscription_purchase_report_btn);
        subscription_purchase_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = getDto()
                        .setServiceCode("119")
                        .setProductCode("TIME_BASED_DAILY_119_119")
                        .setOperator("BANK")
                        .setUserNumber("989122655214");

                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.subscriptionPurchaseReport(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling Subscription Purchase Report Success");
                        Toast.makeText(getBaseContext(), "گزارش گرفته شد", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling Subscription Purchase Report Failed");
                        Toast.makeText(getBaseContext(), "گزارش گرفته نشد", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private InternalPurchaseInformationDto getDto() {
        return new InternalPurchaseInformationDto()
                .setRefreshToken(REFRESH_TOKEN)
                .setClientId(CLIENT_ID)
                .setClientSecretId(SECRET_ID);
    }
}
