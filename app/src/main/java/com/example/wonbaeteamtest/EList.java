package com.example.wonbaeteamtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EList extends Fragment {

    private ArrayList<ShelterData> mData=new ArrayList<ShelterData>();
    private ListView mList;
    private MyAdapter mAdapter;
    ArrayList<ShelterData> arraylist;

    MenuItem mSearch;

    public static void putExtraInfo(Intent intent, String name, String address, String provider){
        //putExtra함수를 묶어버림
        intent.putExtra("name",name);
        intent.putExtra("address",address);
        intent.putExtra("provider",provider);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EList() {


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EList.
     */
    // TODO: Rename and change types and number of parameters
    public static EList newInstance(String param1, String param2) {
        EList fragment = new EList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mList = (ListView) container.findViewById(R.id.shetList);
        mAdapter = new MyAdapter(this, mData);
        mList.setAdapter(mAdapter);
        arraylist = new ArrayList<ShelterData>();
        arraylist.addAll(mData);
        // 리스트의 모든 데이터를 arraylist에 복사한다.// mdata 복사본을 만든다.


        //리스트뷰 클릭 이벤트
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*리스트 뷰가 눌리면 해당 객체 정보를 대피소 보기 엑티비티로 보냄*/
                Intent intent = new Intent(view.getContext(), ShelterInpo.class);
                intent.putExtra("position", position);
                putExtraInfo(intent, mData.get(position).name, mData.get(position).address, mData.get(position).provider);
                startActivityForResult(intent, 0);
            }
        });


        return null;
    }
}