package com.neomechanical.neoperformance.performance.smart.smartNotifier.report;

import com.neomechanical.neoconfig.neoutils.kyori.adventure.text.Component;
import com.neomechanical.neoconfig.neoutils.kyori.adventure.text.TextComponent;
import com.neomechanical.neoconfig.neoutils.messages.MessageUtil;
import com.neomechanical.neoperformance.performance.smart.smartNotifier.managers.LagData;
import com.neomechanical.neoperformance.utils.messages.Messages;
import org.bukkit.entity.Player;

import java.util.List;

public class LagReportBuilder {
    final TextComponent.Builder builder = Component.text();

    public void addData(List<LagData> dataList) {
        for (LagData data : dataList) {
            //Null means there is no data to report
            if (data == null) {
                continue;
            }
            TextComponent.Builder dataComponent = data.getMessageData();
            builder.append(Component.text("  ")).append(Component.text("[")).append(Component.text(data.getDataName()))
                    .append(Component.text("]"))
                    .append(Component.newline());
            builder.append(dataComponent);
            if (dataList.indexOf(data) != dataList.size() - 1) {
                builder.append(Component.newline());
            }
        }
    }

    public void sendReport(Player player) {
        TextComponent message = builder.build();
        if (message.children().size() > 0) {
            MessageUtil.send(player, Messages.MAIN_LAG_REPORT_PREFIX);
            MessageUtil.sendMM(player, message);
            MessageUtil.send(player, Messages.MAIN_SUFFIX);
        }
    }
}
