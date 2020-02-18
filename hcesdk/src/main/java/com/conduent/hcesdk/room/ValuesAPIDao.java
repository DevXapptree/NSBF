package com.conduent.hcesdk.room;

import android.arch.persistence.room.*;
import com.conduent.hcesdk.room.tables.*;

import java.util.List;

@Dao
public abstract class ValuesAPIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertVersion(VersionTable data);

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


    @Transaction
    public void insertReplaceVersion(VersionTable data) {
        deleteAllVersion();
        insertVersion(data);
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

    @Query("DELETE FROM version_table")
    public abstract void deleteAllVersion();

    @Query("DELETE FROM env_network_id_table")
    public abstract void deleteAllEnvNetwork();

    @Query("DELETE FROM contract_status_table")
    public abstract void deleteAllContractStatus();

    @Query("DELETE FROM contract_customer_profile_table")
    public abstract void deleteAllContractCustomerProfile();

    @Query("DELETE FROM contract_validity_zones")
    public abstract void deleteAllContractValidityZone();

    @Query("DELETE FROM product_table")
    public abstract void deleteAllProducts();

//
//    @Update
//    fun update(recordfile: RecordFile)

    //    @Query("SELECT * FROM record_file WHERE id LIKE :rid")
//    fun getFileById(rid: String): RecordFile
//
    @Query("SELECT * FROM version_table")
    public abstract List<VersionTable> getVersion();

    @Query("SELECT * FROM env_network_id_table")
    public abstract List<EnvNetworkIdTable> getEnvNetwork();

    @Query("SELECT * FROM contract_status_table")
    public abstract List<ContractStatusTable> getStatus();

    @Query("SELECT * FROM contract_customer_profile_table")
    public abstract List<ContractCustomerProfileTable> getProfile();

    @Query("SELECT * FROM contract_validity_zones")
    public abstract List<ContractValidityZonesTable> getZones();

    @Query("SELECT * FROM product_table")
    public abstract List<ProductTable> getProducts();
//
//    @Query("SELECT * FROM record_file WHERE file_upload LIKE :status")
//    fun getOfflineRecordFiles(status: Boolean): List<RecordFile>
//
//    @Query("SELECT COUNT(*) FROM record_file WHERE file_upload LIKE :status")
//    fun getOfflineFileCount(status: Boolean): Int
}
