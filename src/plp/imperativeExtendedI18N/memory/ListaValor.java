package plp.imperativeExtendedI18N.memory;


import plp.imperativeExtendedI18N.expression.Valor;
import plp.imperativeExtendedI18N.util.Lista;

/**
 * Lista encadeada com os valores.
 */
public class ListaValor extends Lista<Valor>{
    /**
     * Construtor default.
     */
    public ListaValor(){
    
    }
    /**
     * Construtor
     * @param valor Cabeça da tail.
     */
    public ListaValor(Valor valor){
        super(valor, new ListaValor());
    }

    /**
     * Construtor
     * @param valor Cabeça da tail.
     * @param listaValor Cauda da tail.
     */
    public ListaValor(Valor valor, ListaValor listaValor){
        super(valor, listaValor);
    }

    /**
     * Método utilizado para ir enfileirando os valores.
     * @param valor O valor a ser adicionado a tail de valores.
     */
    public void write(Valor valor) {
        if(getHead() == null) {
            this.head = valor;
            this.tail = new ListaValor();
        }
        else {
            ((ListaValor)getTail()).write(valor);
        }
    }
	
}
