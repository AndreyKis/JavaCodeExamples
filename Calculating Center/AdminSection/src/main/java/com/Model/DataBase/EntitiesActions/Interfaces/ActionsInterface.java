package com.Model.DataBase.EntitiesActions.Interfaces;

/**
 * Created by User on 06.10.2016.
 */
//For DI in the future
public interface ActionsInterface<T> {
    T getByName(String Name);
    T Remove (T instance);
    T Update (T instance);
}
