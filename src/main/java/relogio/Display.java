package relogio;

import edu.princeton.cs.algs4.Draw;
import java.awt.*;
import java.util.ArrayList;

public class Display {
    private Draw desenho;
    private ArrayList<int[]> listaSeg;

    public Display() {
        this.desenho = new Draw();
        this.desenho.clear(Color.white);

        this.desenho.setXscale(0, 12);
        this.desenho.setYscale(0, 8);
        this.desenho.enableDoubleBuffering();

        this.listaSeg = new ArrayList<>();
    }

    private void desenhaHorizontal(double xHorizontal, double yHorizontal, int[] seg){
        // Desenha os segmentos G, D e A
        for(int i = 0; i < seg.length; i += 3){
            double[] x = {xHorizontal, .1 + xHorizontal, .9 + xHorizontal, 1 + xHorizontal, .9 + xHorizontal, .1 + xHorizontal, xHorizontal};
            double[] y = {yHorizontal, .1 + yHorizontal, .1 + yHorizontal, yHorizontal, yHorizontal - .1, yHorizontal - .1, yHorizontal};

            if(seg[i] == 1){
                this.desenho.setPenColor(Color.red);
            } else
                this.desenho.setPenColor(Color.gray);

            this.desenho.filledPolygon(x, y);

            this.desenho.show();

            // O segmento D fica abaixo do G e o A, acima dele
            if(i == 0){
                yHorizontal -= 1;   // Posiciona o segmento D
            } else
                yHorizontal += 2;   // Posiciona o segmento A
        }
    }

    private void desenhaVertical(double xVertical, double yVertical, int[] seg){
        // Desenha os segmentos F, E, C e B
        for(int i = 1; i < seg.length - 1; i++){
            // Ignora o segmento D
            if(i == 3){
                i = 4;
            }

            double[] x = {xVertical, .1 + xVertical, .1 + xVertical, xVertical, xVertical - .1, xVertical - .1, xVertical};
            double[] y = {yVertical, .1 + yVertical, .9 + yVertical, 1 + yVertical, .9 + yVertical, .1 + yVertical, yVertical};

            if(seg[i] == 1){
                this.desenho.setPenColor(Color.red);
            } else
                this.desenho.setPenColor(Color.gray);

            this.desenho.filledPolygon(x, y);
            this.desenho.show();

            // A posicao dos segmentos verticais muda no sentido anti-horario
            switch (i){
                case 1:
                    yVertical -= 1; // Posiciona o segmento E
                    break;

                case 2:
                    xVertical += 1.06;  // Posiciona o segmento C
                    break;

                case 4:
                    yVertical += 1; // Posiciona o segmento B
                    break;
            }
        }
    }

    public void desenhaDisplays(){
        double xHorizontal = 0.5;
        double yHorizontal = 4.5;

        double xVertical = 0.475;
        double yVertical = 4.5;

        this.desenho.setPenColor(Color.red);

        // Desenha a separacao entre horas e minutos
        this.desenho.filledCircle(3.75, 4, .2);
        this.desenho.filledCircle(3.75, 5, .2);

        // Desenha a separacao entre minutos e segundos
        this.desenho.filledCircle(7.75, 4, .2);
        this.desenho.filledCircle(7.75, 5, .2);

        int prox = 1;

        for(int[] seg : this.listaSeg){
            desenhaHorizontal(xHorizontal, yHorizontal, seg);
            desenhaVertical(xVertical, yVertical, seg);

            if(prox % 2 == 0){
                xHorizontal += 2.5;
                xVertical += 2.5;
            } else{
                xHorizontal += 1.5;
                xVertical += 1.5;
            }

            prox++;
        }

        this.listaSeg.clear();
    }

    public void dec2bcd(int[] valores){

        for(int i = 0; i < valores.length; i++){
            int[] segmentos;

            // Display catodo comum
            switch (valores[i]){
                case 0:
                    //                    g  f  e  d  c  b  a
                    segmentos = new int[]{0, 1, 1, 1, 1, 1, 1};
                    break;

                case 1:
                    segmentos = new int[]{0, 0, 0, 0, 1, 1, 0};
                    break;

                case 2:
                    segmentos = new int[]{1, 0, 1, 1, 0, 1, 1};
                    break;

                case 3:
                    segmentos = new int[]{1, 0, 0, 1, 1, 1, 1};
                    break;

                case 4:
                    segmentos = new int[]{1, 1, 0, 0, 1, 1, 0};
                    break;

                case 5:
                    segmentos = new int[]{1, 1, 0, 1, 1, 0, 1};
                    break;

                case 6:
                    segmentos = new int[]{1, 1, 1, 1, 1, 0, 1};
                    break;

                case 7:
                    segmentos = new int[]{0, 0, 0, 0, 1, 1, 1};
                    break;

                case 8:
                    segmentos = new int[]{1, 1, 1, 1, 1, 1, 1};
                    break;

                case 9:
                    segmentos = new int[]{1, 1, 0, 1, 1, 1, 1};
                    break;

                // Caso dê um erro, aparecerá um E no display
                default:
                    segmentos = new int[]{1, 1, 1, 1, 0, 0, 1};
                    break;
            }

            this.listaSeg.add(segmentos);
        }
    }
}
