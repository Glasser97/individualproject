package com.coderglasser.individualproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TheSum.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TheSum#newInstance} factory method to
 * create an instance of this fragment.
 */


public class TheSum extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private List<Data> fData = null;
    public double input = 0;
    public double output = 0;
    public double rest = 0;
    public double inputChange=0;
    public double outputChange=0;


    private OnFragmentInteractionListener mListener;

    public TheSum() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param //param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment TheSum.
     */
    // TODO: Rename and change types and number of parameters
    public static TheSum newInstance(double inputChange, double outputChange) {
        TheSum fragment = new TheSum();
        Bundle args = new Bundle();
        args.putDouble("inputChange", inputChange);
        args.putDouble("outputChange", outputChange);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            inputChange = getArguments().getDouble("inputChange");
            outputChange = getArguments().getDouble("outputChange");
        }
        //连接到数据库
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(getActivity(),"Record_db",null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        DataDao dataDao = daoSession.getDataDao();
        //连接数据库完成

        fData = dataDao.loadAll();
        for (int i=0;i<fData.size();++i){
            if (fData.get(i).getMount().contains("-")){
                output = output+parseDouble(fData.get(i).getMount());
            }else{
                input = input+parseDouble(fData.get(i).getMount());
            }
        }
//        input = input+inputChange;
//        output = output+outputChange;
        rest = input+output;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_the_sum,container,false);
        // Inflate the layout for this fragment
        TextView inputNum = (TextView) view.findViewById(R.id.input_sum);
        TextView outputNum = (TextView) view.findViewById(R.id.output_sum);
        TextView restNum = (TextView) view.findViewById(R.id.rest_sum);

        inputNum.setText(String.valueOf(input));
        outputNum.setText(String.valueOf(output).substring(1));
        restNum.setText(String.valueOf(rest));
        Bundle bundle=new Bundle();
        bundle.putDouble("input",input);
        bundle.putDouble("output",output);
        bundle.putDouble("rest",rest);
        mListener.onFragmentInteraction(bundle);
        Log.d("restNum",String.valueOf(output).substring(1));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Bundle bundle);
    }
}
