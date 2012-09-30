package sod.service;

import java.io.IOException;
import java.util.ArrayList;

import sod.common.Constants;
import sod.common.Storage;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class TVServiceListActivity extends ListActivity {
	
	private ArrayList<String> list;
	private ArrayAdapter<String> adapter;
	Storage rootStorage;
	String serviceName;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		serviceName = adapter.getItem(position);
		
		new AlertDialog.Builder(TVServiceListActivity.this)
		.setTitle("서비스 삭제")
		.setMessage("삭제하시겠습니까?")
		.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				try {
					Storage.destroy(serviceName);
					updateServiceList();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					Constants.logger.log("(debug:client) Storage Destroy IllegalArgumentException");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Constants.logger.log("(debug:client) Storage Destroy IOException");
				}
				
			}
		})
		.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//not To do..
			}
		})
		.show();
		
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tvservicelist);

		list = new ArrayList<String>();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		
		updateServiceList();
		
		
	}//end onCreate
	
	void updateServiceList() {
		try {

			list.clear();//init
			
			if (Storage.checkStorageIs(""))
				rootStorage = Storage.getStorage("");
			else
				rootStorage = Storage.createStorage("");

			String[] serviceNameList = rootStorage.getFileList();

			for (String serviceName : serviceNameList) {
				list.add(serviceName);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Constants.logger.log("(debug:client) getStorage, CreateStorage IllegalArgumentException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Constants.logger.log("(debug:client) getStorage, CreateStorage IOException");
		}

		adapter.notifyDataSetChanged();
	}
}
