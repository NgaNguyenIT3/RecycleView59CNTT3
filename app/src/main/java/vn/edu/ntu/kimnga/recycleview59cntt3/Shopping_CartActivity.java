package vn.edu.ntu.kimnga.recycleview59cntt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.kimnga.controller.ICartController;
import vn.edu.ntu.kimnga.model.Product;

public class Shopping_CartActivity extends AppCompatActivity {
    TextView txtHienThi;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping__cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Gắn mũi tên quay lại
        addViews();
        addEvents();
        Product p;
    }
    private void addViews(){
        txtHienThi = findViewById(R.id.txtHienThi);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        viewCartInfo();

    }
    private void addEvents(){
        /*btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ICartController controller = (ICartController) getApplication();
                if(controller.getShoppingCart().size()>0)

                else
                    Toast.makeText(Shopping_CartActivity.this,
                            "Không có mặt hàng nào",
                            Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    private  void viewCartInfo()
    {
        ICartController controller = (ICartController)getApplication();
        ArrayList<Product> listProducts = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for(Product p: listProducts){
            builder.append(p.getName() + "\t\t\t" +  p.getPrice() + " vnd\n");
        }
        if (builder.toString().length()>0)
            txtHienThi.setText(builder.toString());
        else
            txtHienThi.setText("Không có mặt hàng nào trong giỏ hàng");
    }
}
