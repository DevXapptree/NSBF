package com.conduent.hcesdk.room;

import android.arch.persistence.room.*;
import com.conduent.hcesdk.room.tables.*;

import java.util.List;

@Dao
public abstract class ValuesAPIDao {
    /*Insert Data*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertVersion(VersionTable data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllEnvAppIssuerId(List<EnvApplicationIssuerIdTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllHolderDataCardStatus(List<HolderDataCardStatusTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllHolderDataCommercialId(List<HolderDataCommercialTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllHolderProfileNumber(List<HolderProfileNumberTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertEnvNetworkId(EnvNetworkIdTable data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllContractStatus(List<ContractStatusTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllContractCustomerProfile(List<ContractCustomerProfileTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllContractValidityZone(List<ContractValidityZonesTable> data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllProducts(List<ProductTable> data);

    /*Do Transactions*/
    @Transaction
    public void insertReplaceVersion(VersionTable data) {
        deleteAllVersion();
        insertVersion(data);
    }

    @Transaction
    public void insertReplaceEnvAppIssuerIdBatch(List<EnvApplicationIssuerIdTable> data) {
        deleteAllEnvAppIssuerIds();
        insertAllEnvAppIssuerId(data);
    }

    @Transaction
    public void insertReplaceHolderCardStatusBatch(List<HolderDataCardStatusTable> data) {
        deleteAllHolderDataCardStatus();
        insertAllHolderDataCardStatus(data);
    }

    @Transaction
    public void insertReplaceHolderDataCommercialIdBatch(List<HolderDataCommercialTable> data) {
        deleteAllHolderDataCommercialID();
        insertAllHolderDataCommercialId(data);
    }

    @Transaction
    public void insertReplaceHolderProfileNumberBatch(List<HolderProfileNumberTable> data) {
        deleteAllHolderProfileNumbers();
        insertAllHolderProfileNumber(data);
    }

    @Transaction
    public void insertReplaceEnvNetwork(EnvNetworkIdTable data) {
        deleteAllEnvNetwork();
        insertEnvNetworkId(data);
    }

    @Transaction
    public void insertContractStatusBatch(List<ContractStatusTable> data) {
        deleteAllContractStatus();
        insertAllContractStatus(data);
    }

    @Transaction
    public void insertContractCustomerProfileBatch(List<ContractCustomerProfileTable> data) {
        deleteAllContractCustomerProfile();
        insertAllContractCustomerProfile(data);
    }

    @Transaction
    public void insertContractValidityZoneBatch(List<ContractValidityZonesTable> data) {
        deleteAllContractValidityZone();
        insertAllContractValidityZone(data);
    }

    @Transaction
    public void insertProductsBatch(List<ProductTable> data) {
        deleteAllProducts();
        insertAllProducts(data);
    }

    /*Delete Data*/
    @Query("DELETE FROM version_table")
    public abstract void deleteAllVersion();

    @Query("DELETE FROM env_network_id_table")
    public abstract void deleteAllEnvNetwork();

    @Query("DELETE FROM env_application_issuer_id_table")
    public abstract void deleteAllEnvAppIssuerIds();

    @Query("DELETE FROM holder_data_card_status_table")
    public abstract void deleteAllHolderDataCardStatus();

    @Query("DELETE FROM holder_data_commercial_table")
    public abstract void deleteAllHolderDataCommercialID();

    @Query("DELETE FROM holder_profile_number_table")
    public abstract void deleteAllHolderProfileNumbers();

    @Query("DELETE FROM contract_status_table")
    public abstract void deleteAllContractStatus();

    @Query("DELETE FROM contract_customer_profile_table")
    public abstract void deleteAllContractCustomerProfile();

    @Query("DELETE FROM contract_validity_zones")
    public abstract void deleteAllContractValidityZone();

    @Query("DELETE FROM product_table")
    public abstract void deleteAllProducts();

    /*Get Data*/
    @Query("SELECT * FROM version_table")
    public abstract List<VersionTable> getVersion();

    @Query("SELECT * FROM env_network_id_table")
    public abstract List<EnvNetworkIdTable> getEnvNetwork();

    @Query("SELECT * FROM env_application_issuer_id_table")
    public abstract List<EnvApplicationIssuerIdTable> getEnvIssuer();

    @Query("SELECT * FROM holder_data_card_status_table")
    public abstract List<HolderDataCardStatusTable> getHolderCardStatus();

    @Query("SELECT * FROM holder_data_commercial_table")
    public abstract List<HolderDataCommercialTable> getHolderCommercial();

    @Query("SELECT * FROM holder_profile_number_table")
    public abstract List<HolderProfileNumberTable> getHolderProfile();

    @Query("SELECT * FROM contract_status_table")
    public abstract List<ContractStatusTable> getStatus();

    @Query("SELECT * FROM contract_customer_profile_table")
    public abstract List<ContractCustomerProfileTable> getProfile();

    @Query("SELECT * FROM contract_validity_zones")
    public abstract List<ContractValidityZonesTable> getZones();

    @Query("SELECT * FROM product_table")
    public abstract List<ProductTable> getProducts();

    /*Get Data*/

    @Query("SELECT card_status_value FROM holder_data_card_status_table WHERE card_status_id LIKE :statusID")
    public abstract String getHolderDataCardStatusById(String statusID);

    @Query("SELECT commercial_value FROM holder_data_commercial_table WHERE commercial_id LIKE :commercialID")
    public abstract String getHolderDataCommercialById(String commercialID);

    @Query("SELECT * FROM product_table WHERE productId LIKE :productID")
    public abstract List<ProductTable> getProductByProductId(String productID);

    @Query("SELECT contract_status_value FROM contract_status_table WHERE contract_status_id LIKE :statusID")
    public abstract String getContractStatusById(String statusID);

    @Query("SELECT zone_zoneLabel FROM contract_validity_zones WHERE zone_id LIKE :zoneID")
    public abstract String getContractZoneById(String zoneID);
}
