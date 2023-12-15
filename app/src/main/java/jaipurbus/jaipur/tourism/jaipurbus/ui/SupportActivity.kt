package jaipurbus.jaipur.tourism.jaipurbus.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codersworld.jplibs.beans.CommonBean
import com.codersworld.jplibs.listeners.OnResponse
import com.codersworld.jplibs.rest.ApiCall
import com.codersworld.jplibs.rest.UniverSelObjct
import com.codersworld.jplibs.storage.UserSessions
import com.codersworld.jplibs.utils.CommonMethods
import com.codersworld.jplibs.utils.Tags
import jaipurbus.jaipur.tourism.jaipurbus.R
import jaipurbus.jaipur.tourism.jaipurbus.databinding.ActivitySupportBinding
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper
import jaipurbus.jaipur.tourism.jaipurbus.utils.JBWatcher


class SupportActivity : BaseActivity(),OnResponse<UniverSelObjct>  {
     lateinit var binding:ActivitySupportBinding
    var strEmail = ""
    var strMessage = ""
    var strName = ""
    var errorMsg = ""
    var errorEditText: EditText? = null

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiHelper.setApplicationlanguage(this, UserSessions().getLanguage(this))
        binding = DataBindingUtil.setContentView(this@SupportActivity,R.layout.activity_support)
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.txtBack.setOnClickListener {
            finish()
        }
        binding.btnSend.setOnClickListener {
            makeSupport()
        }
        binding.etName.addTextChangedListener(JBWatcher(this@SupportActivity,binding.etName,binding.imgRightName,1))
        binding.etEmail.addTextChangedListener(JBWatcher(this@SupportActivity,binding.etEmail,binding.imgRightEmail,1))
    }
    fun makeSupport(){
         strName =binding.etName.text.toString().trim()
         strEmail =binding.etEmail.text.toString().trim()
         strMessage =binding.etMessage.text.toString().trim()
        if (this.strName.isEmpty()) {
            this.errorMsg = getString(R.string.error_fullname);
            this.errorEditText = this.binding.etName;
        } else if (this.strName.length < 5) {
            this.errorMsg = getString(R.string.error_valid_name);
            this.errorEditText = this.binding.etName;
        } else if (this.strEmail.isEmpty()) {
            this.errorMsg = getString(R.string.error_email);
            this.errorEditText = this.binding.etEmail;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(this.binding.etEmail.getText().toString()).matches()) {
            this.errorMsg = getString(R.string.error_valid_email);
            this.errorEditText = this.binding.etEmail;
        }  else if (this.strMessage.isEmpty()) {
            this.errorMsg = getString(R.string.error_message);
            this.errorEditText = this.binding.etMessage;
        } else if (this.strMessage.length < 10) {
            this.errorMsg = getString(R.string.error_valid_message);
            this.errorEditText = this.binding.etMessage;
        }else{
            val lang: String = UserSessions(this@SupportActivity).getLanguage(this@SupportActivity)
            ApiCall(this@SupportActivity).supportTicket(this,true,strName,strEmail,strMessage,lang)
        }
    }

    override fun onSuccess(response: UniverSelObjct) {
        try{
            when(response.methodname){
                Tags.JB_API_SUPPORT->{
                    try{
                        var mCommonBean = response.response as CommonBean
                        if (mCommonBean.status==1){
                            binding.llSuccess.visibility=View.VISIBLE
                            binding.llTicket.visibility=View.GONE
                            if (mCommonBean.ticket !=null && CommonMethods.isValidString(mCommonBean.ticket.id)){
                                binding.txtTicketId.visibility=View.VISIBLE
                                binding.txtTicketId.setText(getString(R.string.lbl_ticket,mCommonBean.ticket.id))
                            }else{
                                binding.txtTicketId.visibility=View.INVISIBLE
                            }
                            binding.txtMessage.setText(mCommonBean.msg)
                        }else{
                            CommonMethods.errorDialog(this@SupportActivity,mCommonBean.msg,getString(R.string.app_name),getString(R.string.lbl_ok))
                        }
                    }catch (ex1:Exception){
                        ex1.printStackTrace()
                        CommonMethods.errorDialog(this@SupportActivity,getString(R.string.something_wrong),getString(R.string.app_name),getString(R.string.lbl_ok))
                    }
                }
            }
        }catch (ex:Exception){
            ex.printStackTrace()
            CommonMethods.errorDialog(this@SupportActivity,getString(R.string.something_wrong),getString(R.string.app_name),getString(R.string.lbl_ok))
        }
    }

    override fun onError(type: String?, error: String?) {
     }
}