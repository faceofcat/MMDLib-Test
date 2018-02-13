package net.ndrei.mmdlibtest.tiles;

import java.util.Arrays;
import java.util.List;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.LabelWidgetGui;
import com.mcmoddev.lib.container.gui.TextButtonWidgetGui;
import com.mcmoddev.lib.container.gui.TextEditWidgetGui;
import com.mcmoddev.lib.container.gui.layout.HorizontalStackLayout;
import com.mcmoddev.lib.container.gui.layout.VerticalStackLayout;
import com.mcmoddev.lib.container.gui.util.Padding;
import com.mcmoddev.lib.container.widget.ActionWidget;
import com.mcmoddev.lib.container.widget.DataWidget;
import com.mcmoddev.lib.container.widget.IWidget;
import com.mcmoddev.lib.tile.MMDTileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiTestTileB extends MMDTileEntity {
    @SuppressWarnings("WeakerAccess")
    private class Data extends DataWidget {
        private int someValue;
        private float someFloatValue;

        @DataField public String someOtherValue;

        @DataField public String textA;
        @DataField public String textB;
        @DataField public String textC;

        Data(String key) {
            super(key);
        }

        @DataGetter
        public int getSomeValue() { return this.someValue; }

        @DataSetter
        public void setSomeValue(int value) { this.someValue = value; }

        @DataGetter("floatValue")
        public float getFloat() { return this.someFloatValue; }

        @DataSetter("floatValue")
        public void setSomeFloat(float value) { this.someFloatValue = value; }
    }

    private Data dataWidget = new Data("data");

    @Override
    public List<IWidget> getWidgets(GuiContext context) {
        return Arrays.asList(
            this.dataWidget,
            this.dataWidget.getStringUpdateActionWidget("data_textA", "textA"),
            this.dataWidget.getStringUpdateActionWidget("data_textC", "textC"),
            new ActionWidget("action")
                .setServerSideConsumer(() -> this.dataWidget.setSomeValue(this.dataWidget.getSomeValue() + 42))
        );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IWidgetGui getRootWidgetGui(GuiContext context) {

        return new VerticalStackLayout() {{
            addPiece(new TextEditWidgetGui(100)
                .connectActionWidget("data_textA")
                .setPadding(Padding.bottom(5))
            );
            addPiece(new HorizontalStackLayout() {{
                    addPiece(new LabelWidgetGui("SERVER: "));
                    addPiece(new LabelWidgetGui()
                        .connectDataWidget("data", "textA")
                    );
                }}
                    .setPadding(new Padding(0, 0, 0, 10))
            );

            addPiece(new TextEditWidgetGui(100)
                .connectDataWidget("data", "textB")
                .setMaxLength(100)
                .setPadding(Padding.bottom(5))
            );
            addPiece(new HorizontalStackLayout() {{
                    addPiece(new LabelWidgetGui("CLIENT: "));
                    addPiece(new LabelWidgetGui()
                        .connectDataWidget("data", "textB")
                    );
                }}
                    .setPadding(new Padding(0, 0, 0, 10))
            );

            addPiece(new TextEditWidgetGui(100)
                .connectDataWidget("data", "textC")
                .connectActionWidget("data_textC")
                .setMaxLength(100)
                .setPadding(Padding.bottom(5))
            );
            addPiece(new HorizontalStackLayout() {{
                    addPiece(new LabelWidgetGui("CLIENT: "));
                    addPiece(new LabelWidgetGui()
                        .connectDataWidget("data", "textC")
                    );
                }}
                    .setPadding(new Padding(0, 0, 0, 10))
            );
            addPiece(new HorizontalStackLayout() {{
                    addPiece(new LabelWidgetGui("SERVER: "));
                    addPiece(new LabelWidgetGui()
                        .connectDataWidget("data", "textC")
                    );
                }}
                    .setPadding(new Padding(0, 0, 0, 10))
            );

            addPiece(new HorizontalStackLayout() {{
                addPiece(new TextButtonWidgetGui("Click me!")
                    .connectToWidget("action")
                );
                addPiece(new LabelWidgetGui()
                    .connectDataWidget("data", "someValue")
                    .setPadding(new Padding(10, 0))
                );
            }});
        }};
    }
}
