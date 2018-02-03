package net.ndrei.mmdlibtest.tiles;

import java.util.Arrays;
import java.util.List;
import com.mcmoddev.lib.MMDLib;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.TextButtonWidgetGui;
import com.mcmoddev.lib.container.gui.TextWidgetGui;
import com.mcmoddev.lib.container.gui.layout.HorizontalStackLayout;
import com.mcmoddev.lib.container.gui.util.Padding;
import com.mcmoddev.lib.container.widget.ActionWidget;
import com.mcmoddev.lib.container.widget.DataWidget;
import com.mcmoddev.lib.container.widget.IWidget;
import com.mcmoddev.lib.tile.MMDTileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiTestTileB extends MMDTileEntity {
    private DataWidget dataWidget = new DataWidget("data") {
        private int someValue;
        private float someFloatValue;

        @DataField
        public String someOtherValue;

        @DataGetter
        public int getSomeValue() { return this.someValue; }

        @DataSetter
        public void setSomeValue(int value) { this.someValue = value; }

        @DataGetter("floatValue")
        public float getFloat() { return this.someFloatValue; }

        @DataSetter("floatValue")
        public void setSomeFloat(float value) { this.someFloatValue = value; }
    };

    @Override
    public List<IWidget> getWidgets(GuiContext context) {
        return Arrays.asList(
            this.dataWidget,
            new ActionWidget("action")
                .setClientSideConsumer(() -> MMDLib.logger.info("Hello from client side!"))
                .setServerSideConsumer(() -> {
                    MMDLib.logger.info("Hello from server side!");
                    //noinspection ConstantConditions
                    this.dataWidget.setValue("someValue",
                        this.dataWidget.getValue(Integer.class, "someValue") + 42);
            })
        );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IWidgetGui getRootWidgetGui(GuiContext context) {
        HorizontalStackLayout layout = new HorizontalStackLayout();

        layout.addPiece(new TextButtonWidgetGui("Click me!")
            .setClickAction(() -> MMDLib.logger.info("Hello from GUI side!"))
            .connectToWidget("action")
        );

        layout.addPiece(new TextWidgetGui()
            .connectDataWidget("data", "someValue")
            .setPadding(new Padding(10, 0))
        );

        return layout;
    }
}
