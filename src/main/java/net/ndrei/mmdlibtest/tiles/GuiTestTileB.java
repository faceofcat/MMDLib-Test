package net.ndrei.mmdlibtest.tiles;

import java.util.ArrayList;
import java.util.List;
import com.mcmoddev.lib.MMDLib;
import com.mcmoddev.lib.container.gui.GuiContext;
import com.mcmoddev.lib.container.gui.IWidgetGui;
import com.mcmoddev.lib.container.gui.TextButtonWidgetGui;
import com.mcmoddev.lib.container.gui.layout.VerticalStackLayout;
import com.mcmoddev.lib.container.widget.ActionWidget;
import com.mcmoddev.lib.container.widget.IWidget;
import com.mcmoddev.lib.tile.MMDTileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiTestTileB extends MMDTileEntity {
    @Override
    public List<IWidget> getWidgets(GuiContext context) {
        return new ArrayList<IWidget>() {{
            add(new ActionWidget("action")
                .setClientSideConsumer(() -> MMDLib.logger.info("Hello from client side!"))
                .setServerSideConsumer(() -> MMDLib.logger.info("Hello from server side!"))
            );
        }};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IWidgetGui getRootWidgetGui(GuiContext context) {
        VerticalStackLayout layout = new VerticalStackLayout();

        layout.addPiece(new TextButtonWidgetGui("Click me!", 100)
            .setClickAction(() -> MMDLib.logger.info("Hello from GUI side!"))
            .connectToWidget("action")
        );

        return layout;
    }
}
