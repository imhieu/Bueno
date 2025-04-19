package me.hieu.kinder.profile;

import me.hieu.kinder.Bueno;
import me.hieu.libraries.drink.argument.CommandArg;
import me.hieu.libraries.drink.exception.CommandExitMessage;
import me.hieu.libraries.drink.parametric.DrinkProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hieu
 * @date 13/09/2023
 */

public class ProfileProvider extends DrinkProvider<Profile> {

    @Override
    public boolean doesConsumeArgument() {
        return true;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean allowNullArgument() {
        return true;
    }

    @Nullable
    @Override
    public Profile defaultNullValue() {
        return null;
    }

    @Nullable
    @Override
    public Profile provide(@Nonnull CommandArg arg, @Nonnull List<? extends Annotation> annotations) throws CommandExitMessage {
        String name = arg.get();
        if (name == null) return null;
        try {
            return Bueno.getInstance().getProfileHandler().getProfileByName(name);
        } catch (IOException e) {
            throw new CommandExitMessage("No profile with the name '" + name + "'.");
        }
    }

    @Override
    public String argumentDescription() {
        return "profile";
    }

    @Override
    public List<String> getSuggestions(@Nonnull String prefix) {
        final String finalPrefix = prefix.toLowerCase();
        List<String> suggestions = new ArrayList<>();
        for (Profile profile : Bueno.getInstance().getProfileHandler().getProfileList()){
            if (profile.getName().toLowerCase().startsWith(finalPrefix)) suggestions.add(profile.getName());
        }
        return suggestions;
    }
}
