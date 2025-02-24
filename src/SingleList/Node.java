package SingleList;

/*Напишите реализацию однонаправленного списка без использования Collection и
 массивов. Должны быть методы добавления, удаления и поиска элементов.
** реализовать все операции по индексу*/
public class Node<E> {
    E item;
    Node<E> next;

    public Node(E item) {
        this.item = item;
        this.next = null;
    }

}
