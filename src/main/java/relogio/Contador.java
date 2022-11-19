package relogio;

public class Contador{
    private int maxD;
    private int maxU;

    private int dezena;
    private int unidade;

    private boolean flag;

    public int getDezena() {
        return this.dezena;
    }

    public int getUnidade() {
        return this.unidade;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public Contador(int maxD, int maxU, int dezena, int unidade) {
        if(maxD > 9){
            this.maxD = 9;
        } else
            this.maxD = maxD;

        if(maxU > 9){
            this.maxU = 9;
        } else
            this.maxU = maxU;

        if(dezena > this.maxD){
            this.dezena = this.maxD;
        } else
            this.dezena = dezena;

        if(unidade > this.maxU){
            this.unidade = this.maxU;
        } else
            this.unidade = unidade;
    }

    public void conta(){
        // Se chegar no valor estipulado, reseta a unidade e a dezena e aciona uma flag de indicacao
        if(this.dezena == this.maxD && this.unidade == this.maxU){
            this.unidade = 0;
            this.dezena = 0;

            this.flag = true;

          // Se estourar o valor da unidade, soma uma dezena
        } else if(this.unidade == 9){
            this.unidade = 0;
            this.dezena++;
        } else{
            this.unidade++;

            this.flag = false;
        }
    }
}