package com.example.alvaro.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private TextView output;



    private double actualvalue;
    private double lastvalue;


    private boolean decimal = false;

    public static final int any_op = 0;
    public static final int sum_op = 1;
    public static final int substraccion_op = 2;
    public static final int multiplication_op = 3;
    public static final int division_op = 4;


    private int op;


    public void operate() {
        String temporal;

        switch(op) {
            case division_op:
                if (actualvalue == 0) {
                    delete();
                    return;
                }
                actualvalue = lastvalue / actualvalue;
                break;
            case multiplication_op:
                actualvalue = lastvalue * actualvalue;
                break;
            case substraccion_op:
                actualvalue = lastvalue - actualvalue;
                break;
            case sum_op:
                actualvalue = lastvalue + actualvalue;
                break;
            default:
                
                return;
        }

        lastvalue = 0;
        temporal = Double.toString(actualvalue);
        decimal = temporal.contains(".");
        op = any_op;
        this.output.setText(temporal);
    }

    public void division() {
        if (op != any_op) {
            return;
        }

        op = division_op;

        lastvalue = actualvalue;
        actualvalue = 0.0;

        output.setText("0");
    }

    public void mult() {
        if (op != any_op) {
            
            return;
        }

        op = multiplication_op;

        lastvalue = actualvalue;
        actualvalue = 0.0;

        output.setText("0");
        
    }

    public void rest() {
        if (op != any_op) {
          
            return;
        }

        op = substraccion_op;

        lastvalue = actualvalue;
        actualvalue = 0.0;

        output.setText("0");
      
    }

    public void sum() {
        if (op != any_op) {
           
            return;
        }

        op = sum_op;

        lastvalue = actualvalue;
        actualvalue = 0.0;

        output.setText("0");
       
    }
    
    public void digito(String digito) {
        String resultado = this.output.getText().toString().trim();
        double valor = 0.0;

        if (digito == "." && decimal) {
            return;
        }

        if (resultado.compareTo("") == 0 || resultado.compareTo("0") == 0) {
            if (digito == ".") {
                this.output.setText("0.");
                decimal = true;
                actualvalue = 0;
            } else {
                this.output.setText(digito);
                actualvalue = Double.parseDouble(digito);
            }

            return;
        }

        resultado = resultado + digito;

        try {
            valor = Double.valueOf(resultado);
        } catch (NumberFormatException e) {
            delete();
            return;
        }

        this.output.setText(resultado);
        actualvalue = valor;
    }

  
    public void delete() {
        output.setText("0");

        actualvalue = 0;
        lastvalue = 0;
        decimal = false;

        op = any_op;
    
    }
    
    public void pressedButton(View boton) {
        
        Button b = null;
        String operacion;

        if (boton == null) {
            return;
        }

        try {
            b = (Button) boton;
        } catch (ClassCastException e) {
            return;
        }

        operacion = b.getText().toString();
        switch(operacion) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                digito(operacion);
                break;
            case "+":
                sum();
                break;
            case "-":
                rest();
                break;
            case "x":
                mult();
                break;
            case "/":
                division();
                break;
            case "=":
                operate();
                break;
            default:

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.resultado);
        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        output.setText("0");
        lastvalue = 0.0;
        actualvalue = 0.0;
        decimal = false;
        op = any_op;
    }
}
