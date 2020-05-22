package vn.edu.ntu.kimnga.recycleview59cntt3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.kimnga.controller.ICartController;
import vn.edu.ntu.kimnga.model.Product;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Gắn mũi tên quay lại
    }
    private void addViews() {
        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this));//context this: truyền vào main activity
        ICartController controller = (ICartController) getApplication();
        listProduct = controller.getListProduct();
        adapter = new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnu_cart:
                callShopping_CartActivity();
                break;
            case R.id.mnu_close:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callShopping_CartActivity(){
        Intent intent = new Intent(this, Shopping_CartActivity.class);
        startActivity(intent);
    }

    //Lớp cài đặt cho việc hiển thị của một Product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtPrice, txtDesc;
        ImageButton imBtnCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            //Ctrl+D để sao chép xuống dưới
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imBtnCart = this.itemView.findViewById(R.id.imBtnCart);
            imBtnCart.setOnClickListener(this);
        }

        public void bind(Product p) {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            imBtnCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }

        @Override
        public void onClick(View v) {
            ICartController controller = (ICartController) getApplication();
            if(!controller.addToShoppingCart(p))
                Toast.makeText(MainActivity.this, //Context: MainActiviry or Application or service
                    "SP: " + p.getName() + " đã có trong giỏ hàng",
                    Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,
                        "Đã thêm sp " + p.getName() + " vào giỏ hàng",
                        Toast.LENGTH_SHORT).show();
        }
    }

    //Lớp Adapter kết nối RecyclerView và dữ liệu
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
        ArrayList<Product> listProduct;

        //Hàm khởi tạo Adapter
        public ProductAdapter(ArrayList<Product> listProduct) {

            this.listProduct = listProduct;
        }

        //Tạo một ViewHolder để hiển thị dữ liệu
        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater LayoutInflater = getLayoutInflater();
            //Chuyển Layout đã thiết kế bằng xml thành một đối tượng View
            View view = LayoutInflater.inflate(R.layout.product_item,
                    parent, false);
            return new ProductViewHolder(view);
        }

        //Kết nối một dữ liệu trong danh sách với một ViewHolder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));
        }

        //Trả về danh sách
        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}
