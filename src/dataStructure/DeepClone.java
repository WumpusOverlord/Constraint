package dataStructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * Created by hugo on 3/16/17.
 */
public interface DeepClone {

    /**
     * This method makes a "deep clone" of any Java object it is given.
     *///http://alvinalexander.com/java/java-deep-clone-example-source-code
    static Object DeepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static    LinkedList<Integer> copy(LinkedList<Integer> variable){

      //  VariableWithDomain.Index i = variable.getIndex();

      //  LinkedList<Integer> domainList = new LinkedList<>(variable.getValues());
        LinkedList<Integer> domainList = new LinkedList<>();
for (Integer value: variable){
domainList.add(value);

}

return domainList;

    //    VariableWithDomain newVar = new VariableWithDomain(i, domainList);
      //  return newVar;
    }

}
