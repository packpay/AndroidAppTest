package ir.packpay.packpaysample;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ir.packpay.iablib.data.dto.BaseResponse;
import ir.packpay.iablib.data.dto.ConsumablePurchaseResponse;
import ir.packpay.iablib.data.dto.ReportResponse;
import ir.packpay.iablib.data.dto.ServerResponse;
import ir.packpay.iablib.data.dto.SubscriptionPurchaseResponse;
import ir.packpay.iablib.data.dto.TimeBasedPurchaseResponse;
import ir.packpay.iablib.model.ServiceClient;
import ir.packpay.iablib.handler.AsyncCallback;
import ir.packpay.iablib.data.dto.FaultResponse;
import ir.packpay.iablib.data.dto.InternalPurchaseInformationDto;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private String date2String(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd - HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());

    }


    //site
    private static final String REFRESH_TOKEN = "2bff6cfe-254b-4417-b669-5af1784c7894";
    private static final String CLIENT_ID = "726dBQoVjlHZWeKLcm6U";
    private static final String SECRET_ID = "3vNeY6uqIlj9vEhFH9AvLVR11uT3wb";


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
                        .setServiceCode("138")
                        .setProductCode("ON_DEMAND_138_1541");
//                        .setServiceCode("138")
//                        .setProductCode("TIME_BASED_DAILY_138_1001");
//                        .setViewOneGateway("BANK");
//                        .setMobileNumberMCI("9132957578");
//                        .setMobileNumberTCI("2166054401");
//                        .setMobileNumberMTN("9196565454");



                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);

                serviceClient.purchase(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object res) {
                        Log.i(MainActivity.class.getSimpleName(), "calling purchase Success");
//                       Toast.makeText(getBaseContext(), "خرید با موفقیت انجام شد", Toast.LENGTH_LONG).show();

                        ServerResponse response = (ServerResponse) res;

                        Toast.makeText(getBaseContext(), response.getReferenceCode(), Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling purchase failed");
                        Toast.makeText(getBaseContext(), "خرید ناموفق بود", Toast.LENGTH_LONG).show();


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
                        .setServiceCode("138")
//                        .setViewOneGateway("MCI")
                        .setProductCode("SUBSCRIPTION_DAILY_138_155")
                        .setShowInputNumberPage(false);
//                        .setShowChangeNumberBTN(false)
//                        .setMobileNumberMCI("9132957522");
//                        .setMobileNumberTCI("2166054401")
//                        .setMobileNumberMTN("9144565454");


//                        .setProductCode("SUBSCRIPTION_DAILY_128_135");

                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.subscribe(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling subscribe Success");
                        Toast.makeText(getBaseContext(), "calling subscribe Success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling subscribe failed");
                        Toast.makeText(getBaseContext(), "calling subscribe failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        ///////////////////////////////

        Button get_subscription_status_btn = findViewById(R.id.get_subscription_status_btn);
        get_subscription_status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InternalPurchaseInformationDto dto = getDto()
                        .setServiceCode("138")
                        .setProductCode("SUBSCRIPTION_DAILY_138_155")
                        .setOperator("MCI")
                        .setUserNumber("9132957573");

                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);

                serviceClient.getSubscriptionStatus(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object SubscriptionResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling get Subscription status Success");
                        Toast.makeText(getBaseContext(), "calling get Subscription status Success", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling get Subscription status Failed");
                        Toast.makeText(getBaseContext(), "calling get Subscription status Failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        //////////////////////


        Button consumable_purchase_report_btn = findViewById(R.id.consumable_purchase_report_btn);
        consumable_purchase_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = getDto()
                        .setServiceCode("138")
                        .setProductCode("IN_APP_PURCHASE_138_154")
                        .setOperator("BANK")
                        .setUserNumber("09132957573");


                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.consumablePurchaseReport(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling Consumable Purchase Report Success");
//                        Toast.makeText(getBaseContext(), "Calling Consumable Purchase Report Success", Toast.LENGTH_LONG).show();

                        BaseResponse response = (BaseResponse) serverResponse;
                        ConsumablePurchaseResponse res = (ConsumablePurchaseResponse)response;

//                        Log.i(MainActivity.class.getSimpleName(), "calling Subscription Purchase Report Success");
//                        Toast.makeText(getBaseContext(),res.getMessage(), Toast.LENGTH_LONG).show();

                        String message = res.getMessage();

                        String report = "";


                        report += "firstPurchaseTime : " + date2String(Long.parseLong(res.getFirstPurchaseTime())) + "\n";
                        report += "totalNumberOfPurchases : " + res.getTotalNumberOfPurchases() + "\n";

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle(message);
                        builder1.setMessage(report);
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();



                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling Consumable Purchase Report Failed");
                        Toast.makeText(getBaseContext(), "Calling Consumable Purchase Report Failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


        /////////////////////////time based purchase report
        Button time_based_purchase_report_btn = findViewById(R.id.time_based_purchase_report_btn);
        time_based_purchase_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = getDto()
                        .setServiceCode("138")
                        .setProductCode("TIME_BASED_DAILY_138_1001")
                        .setOperator("BANK")
                        .setUserNumber("09132957573");


                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.timeBasedPurchaseReport(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "timeBased Purchase Report Success");
//                        Toast.makeText(getBaseContext(), "Calling Consumable Purchase Report Success", Toast.LENGTH_LONG).show();

                        BaseResponse response = (BaseResponse) serverResponse;
                        TimeBasedPurchaseResponse res = (TimeBasedPurchaseResponse)response;

//                        Log.i(MainActivity.class.getSimpleName(), "calling Subscription Purchase Report Success");
//                        Toast.makeText(getBaseContext(),res.getMessage(), Toast.LENGTH_LONG).show();

                        String message = res.getMessage();

                        String report = "";


                        report += "purchaseTime : " + date2String(res.getPurchaseTime()) + "\n";;
                        report += "expireTime : " + date2String(res.getExpireTime()) + "\n";

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle(message);
                        builder1.setMessage(report);
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();



                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling timeBasedPurchase Report Failed");
                        Toast.makeText(getBaseContext(), "Calling timeBased Purchase Report Failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });




        Button test_btn = findViewById(R.id.get_report_ref_wallet_btn);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = getDto()
                        .setOperator("TCI")
                        .setServiceCode("138")
                        .setProductCode("TIME_BASED_DAILY_138_1001")
                        .setUserNumber("982160000000");


                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                Long from_date=1561179982606L;
                Long to_date=2561179982606L;
                String ref = "10804810";

                serviceClient.getReportByRefrenceCode(dto,from_date,to_date, ref, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling Consumable Purchase Report Success");
//                        Toast.makeText(getBaseContext(), "Calling Consumable Purchase Report Success", Toast.LENGTH_LONG).show();

                        ReportResponse res = (ReportResponse) serverResponse;



                        String report = "";


                        report += "paymentTime : " + date2String(res.getReportData().get(0).getPaymentTime()) + "\n";;
                        report += "transactionStatus : " + res.getReportData().get(0).getTransactionStatus() + "\n";;
                        report += "chargeType : " + res.getReportData().get(0).getChargeType() + "\n";;
                        report += "amount : " + String.valueOf(res.getReportData().get(0).getAmount()) + "\n";;
                        report += "subscriber : " + res.getReportData().get(0).getSubscriber() + "\n";;

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle(res.getMessage());
                        builder1.setMessage(report);
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();



                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "Calling Consumable Purchase Report Failed");
                        Toast.makeText(getBaseContext(), "Calling Consumable Purchase Report Failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });




        Button btn = findViewById(R.id.subscription_purchase_report_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternalPurchaseInformationDto dto = getDto()
                        .setServiceCode("138")
                        .setProductCode("SUBSCRIPTION_DAILY_138_155")
                        .setOperator("MCI")
                        .setUserNumber("989132957573");

                ServiceClient serviceClient = new ServiceClient(getBaseContext(), dto);
                serviceClient.subscriptionPurchaseReport(dto, new AsyncCallback() {
                    @Override
                    public void onSuccess(Object serverResponse) {
                        BaseResponse response = (BaseResponse)serverResponse;
                        SubscriptionPurchaseResponse res = (SubscriptionPurchaseResponse)response;
                        String message = res.getMessage();

                        String report = "";
                        report += "expire_time : " + date2String(res.getPurchaseData().get(0).getPurchaseTime()) + "\n";;
                        report += "purchase_time : " + date2String(res.getPurchaseData().get(0).getExpireTime()) + "\n";
                        report += "status : " + res.getPurchaseData().get(0).getProductStatus() + "\n";
                        report += "autoCharge : " + res.getPurchaseData().get(0).getAutoCharge();



                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle(message);
                        builder1.setMessage(report);
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();



                    }

                    @Override
                    public void onFailed(FaultResponse faultResponse) {
                        Log.i(MainActivity.class.getSimpleName(), "calling Subscription Purchase Report Failed");
                        Toast.makeText(getBaseContext(), "calling Subscription Purchase Report Failed", Toast.LENGTH_LONG).show();

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
