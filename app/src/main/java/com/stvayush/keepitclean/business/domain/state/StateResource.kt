package com.stvayush.keepitclean.business.domain.state

import android.view.View
import com.stvayush.keepitclean.business.domain.utils.TodoCallback

/** A very wide utils class covering maximum ui common events */

//remove this object qualifier if run into errors
object StateResource {
  data class StateMessage(val response: Response)

  data class Response(
    val message: String?,
    val uiComponentType: UIComponentType,
    val messageType: MessageType
  )

  sealed class UIComponentType {

    class Toast : UIComponentType()

    class Dialog : UIComponentType()

    class AreYouSureDialog(
      val callback: AreYouSureCallback
    ) : UIComponentType()

    class SnackBar(
      val undoCallback: SnackbarUndoCallback? = null,
      val onDismissCallback: TodoCallback? = null
    ) : UIComponentType()

    class None : UIComponentType()
  }

  sealed class MessageType {

    class Success : MessageType()

    class Error : MessageType()

    class Info : MessageType()

    class None : MessageType()
  }

  interface StateMessageCallback {

    fun removeMessageFromStack()
  }

  interface AreYouSureCallback {

    fun proceed()

    fun cancel()
  }

  interface SnackbarUndoCallback {

    fun undo()
  }

  class SnackbarUndoListener
  constructor(
    private val snackbarUndoCallback: SnackbarUndoCallback?
  ) : View.OnClickListener {

    override fun onClick(v: View?) {
      snackbarUndoCallback?.undo()
    }

  }

  interface DialogInputCaptureCallback {

    fun onTextCaptured(text: String)
  }
}