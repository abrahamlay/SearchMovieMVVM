package com.abraham.searchmoviemvvm.ui.main.repository.remotedatasource

/**
 * Created by Abraham on 11/09/2018.
 */
class RemoteCallback{
    interface Load<T> {
        fun onDataLoaded(data: T)

        //        void onFailed(Throwable throwable);
        //
        //        void onDataNotAvailable(String message);
    }

    interface Save<T> {
        fun onDataSaved(data: T)

        fun onSaveFailed(throwable: Throwable)
    }

    interface Update<T> {
        fun onDataUpdated(data: T)

        fun onUpdateFailed(throwable: Throwable)
    }

    interface Delete<T> {
        fun onDataDeleted(data: T)

        fun onDeleteFailed(throwable: Throwable)
    }
}