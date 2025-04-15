package me.hieu.kinder;

import me.hieu.kinder.mongo.MongoHandler;
import me.hieu.libraries.drink.CommandService;
import me.hieu.kinder.util.assemble.Assemble;
import me.hieu.kinder.util.assemble.AssembleStyle;
import lombok.Getter;
import lombok.Setter;
import me.hieu.kinder.board.BoardAdapter;
import me.hieu.kinder.board.BoardHandler;
import me.hieu.kinder.command.*;
import me.hieu.kinder.command.balance.BalanceCommand;
import me.hieu.kinder.command.balance.PayCommand;
import me.hieu.kinder.shop.command.BuyCommand;
import me.hieu.kinder.shop.command.SellCommand;
import me.hieu.kinder.shop.command.ShopCommand;
import me.hieu.kinder.warp.command.WarpCreateCommand;
import me.hieu.kinder.warp.command.WarpsCommand;
import me.hieu.kinder.listener.*;
import me.hieu.kinder.profile.Profile;
import me.hieu.kinder.profile.ProfileHandler;
import me.hieu.kinder.profile.ProfileListener;
import me.hieu.kinder.profile.ProfileProvider;
import me.hieu.kinder.task.MinerTask;
import me.hieu.kinder.task.SaveProfileTask;
import me.hieu.kinder.task.TimeVoteTask;
import me.hieu.libraries.menu.MenuListener;
import me.hieu.kinder.vote.Vote;
import me.hieu.kinder.warp.WarpHandler;
import me.hieu.libraries.Libraries;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;

@Getter @Setter
public class Bueno extends JavaPlugin {

    @Getter @Setter public static Bueno instance;

    public static boolean CREEPER_EXPLOSION;

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    public static long UP_TIME;

    private MongoHandler mongoHandler;
    private BoardHandler boardHandler;
    private ProfileHandler profileHandler;
    private WarpHandler warpHandler;

    private Vote vote = null;

    @Override
    public void onEnable() {
        instance = this;
        registerConfigs();
        registerHandlers();
        registerListeners();
        registerCommands();
        UP_TIME = System.currentTimeMillis();
    }

    private void registerHandlers(){
        mongoHandler = new MongoHandler();
        boardHandler = new BoardHandler();
        profileHandler = new ProfileHandler();
        warpHandler = new WarpHandler();
        warpHandler.loadAllWarps();
        new SaveProfileTask();
        new TimeVoteTask();
        new MinerTask();
        Assemble board = new Assemble(this, new BoardAdapter());
        board.setTicks(2);
        board.setAssembleStyle(AssembleStyle.MODERN);
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new FoundOresListener(), this);
        getServer().getPluginManager().registerEvents(new GeneralListener(), this);
        getServer().getPluginManager().registerEvents(new LunarClientAPIListener(), this);
        getServer().getPluginManager().registerEvents(new ProfileListener(), this);
        getServer().getPluginManager().registerEvents(new MobChangesListener(), this);
        getServer().getPluginManager().registerEvents(new BackpackListner(), this);
    }

    private void registerCommands(){
        CommandService commandService = Libraries.get(this);
        commandService.bind(Profile.class).toProvider(new ProfileProvider());
        commandService.register(new LocationCommand(), "location", "location");
        commandService.register(new TrackCommand(), "track", "track");
        commandService.register(new WarpsCommand(), "warps", "");
        commandService.register(new WarpCreateCommand(), "warp", "");
        commandService.register(new StatisticsCommand(), "statistics", "statistic", "stats", "stat");
        commandService.register(new ShopCommand(), "shop", "");
        commandService.register(new BuyCommand(), "buy", "");
        commandService.register(new SellCommand(), "sell", "");
        commandService.register(new BalanceCommand(), "balance", "bal", "$");
        commandService.register(new PayCommand(), "pay", "");
        commandService.register(new BackpackCommand(), "backpack", "bp");
        commandService.register(new VoteCommand(), "vote", "");
        commandService.register(new CurrencyCommand(), "currency");
        commandService.register(new UptimeCommand(), "uptime");
        commandService.register(new ToggleCommand(), "toggle");
        commandService.registerCommands();
    }

    private void registerConfigs() {
        saveDefaultConfig();
        CREEPER_EXPLOSION = this.getConfig().getBoolean("settings.creeper-explosion");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
