package rj.ersi.mqtt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import rj.ersi.mqtt.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonMqtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MqttClient mqttClient = new MqttClient(binding.mqttEditText.getText().toString());
                String res = mqttClient.publishMessage(binding.mqttMsgEditText.getText().toString());
                binding.textviewResult.setText(res);
            }
        });

        binding.buttonCoap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoAPClient coAPClient = new CoAPClient(binding.coapEditText.getText().toString());
                String res = coAPClient.sendRequest();
                binding.textviewResult.setText(res);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}