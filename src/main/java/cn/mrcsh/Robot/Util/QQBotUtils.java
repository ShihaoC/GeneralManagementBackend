package cn.mrcsh.Robot.Util;

import love.forte.simbot.bot.OriginBotManager;
import love.forte.simbot.message.Messages;
import love.forte.simbot.message.MessagesBuilder;
import love.forte.simbot.message.Text;
import love.forte.simbot.resources.Resource;

import java.io.File;

public class QQBotUtils {
    /**
     * 构造纯文字消息
     * @param args 每一行的问题
     * @return 构造出来的Message对象
     */
    public static Messages build(String ... args){
        MessagesBuilder builder = new MessagesBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(Text.of(args[i]+"\n"));
        }
        return builder.build();
    }
    /**
     * 构造图片文本消息
     * @param image 图片文件对象
     * @param args 每一行的问题
     * @return 构造出来的Message对象
     */
    public static Messages build(File image , String ... args){
        MessagesBuilder builder = new MessagesBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(Text.of(args[i]+"\n"));
        }
        builder.image(Resource.of(image));
        return builder.build();
    }

    /**
     * 提供给Bukkit和Bungee发送消息的API
     * @param GroupId 群号
     * @param Context 内容
     */
    public static void sendMessage(String GroupId,String Context){
        OriginBotManager managers = OriginBotManager.INSTANCE;
        managers.forEach(bots->{
            bots.all().forEach(bot->{
                bot.getGroups().asStream().forEach(group -> {
                    if(group.getId().toString().equals(GroupId)){
                        group.sendBlocking(Context);
                    }
                });
            });
        });
    }
}
