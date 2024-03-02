package com.example.ghosthome.database.model.ghostHomeUser

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ghost_home_user_table")
data class GhostHomeUser(
    @PrimaryKey
    @SerializedName("user_id")
    @ColumnInfo(name ="user_id" )
    @Expose
    val userId : String,

    @SerializedName("user_name")
    @ColumnInfo(name ="user_name" )
    @Expose
    val userName : String,

    @SerializedName("phone_number")
    @ColumnInfo(name ="phone_number" )
    @Expose
    val phoneNumber : String,

    @SerializedName("password")
    @ColumnInfo(name ="password" )
    @Expose
    val password : String,

    @SerializedName("confirm_password")
    @ColumnInfo(name ="confirm_password" )
    @Expose
    val confirmPassword : String,

    @SerializedName("home_name")
    @ColumnInfo(name ="home_name" )
    @Expose
    val homeName : String,

    @SerializedName("home_pin")
    @ColumnInfo(name ="home_pin" )
    @Expose
    val homePin : String,

) {

    override fun toString(): String {
        return "GhostHomeUser(userId = $userId," +
                "\nuserName = $userName," +
                "\nphoneNumber = $phoneNumber," +
                "\npassword = $password," +
                "\nconfirmPassword = $confirmPassword," +
                "\nhomeName = $homeName," +
                "\nhomePin = $homePin)"
    }

}
