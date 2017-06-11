
package axoloti.object.codegenview;

import axoloti.object.AxoObjectInstanceAbstract;
import axoloti.object.AxoObjectInstanceComment;
import axoloti.object.ObjectInstanceController;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author jtaelman
 */
public class AxoObjectInstanceCommentCodegenView extends AxoObjectInstanceAbstractCodegenView {
    
    final ObjectInstanceController controller;
    
    public AxoObjectInstanceCommentCodegenView(AxoObjectInstanceComment model, ObjectInstanceController controller) {
        this.controller = controller;
    }

    @Override
    public AxoObjectInstanceAbstract getModel() {
        return controller.getModel();
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public ObjectInstanceController getController() {
        return controller;
    }

    @Override
    public String GenerateUICode(int[] count) {
        return "";
    }

    @Override
    public String GenerateInitCodePlusPlus(String vprefix, boolean enableOnParent) {
        return "";
    }

    @Override
    public String GenerateDisposeCodePlusPlus(String vprefix) {
        return "";
    }

    @Override
    public String GenerateClass(String ClassName, String OnParentAccess, Boolean enableOnParent) {
        return "";
    }

}