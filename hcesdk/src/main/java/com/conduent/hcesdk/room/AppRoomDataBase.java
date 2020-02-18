package com.conduent.hcesdk.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.conduent.hcesdk.room.tables.*;

@Database(entities = {VersionTable.class, ContractStatusTable.class, ContractCustomerProfileTable.class, ContractValidityZonesTable.class, EnvApplicationIssuerIdTable.class, EnvNetworkIdTable.class, HolderDataCardStatusTable.class, HolderDataCommercialTable.class, HolderProfileNumberTable.class, ProductTable.class}, version = 1, exportSchema = false)
public abstract class AppRoomDataBase extends RoomDatabase {

    public abstract ValuesAPIDao valuesAPIDao();

    private static volatile AppRoomDataBase INSTANCE;

    public static AppRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDataBase.class, "hcesdk_database").fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
