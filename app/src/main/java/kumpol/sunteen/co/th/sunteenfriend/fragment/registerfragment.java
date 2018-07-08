package kumpol.sunteen.co.th.sunteenfriend.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import kumpol.sunteen.co.th.sunteenfriend.MainActivity;
import kumpol.sunteen.co.th.sunteenfriend.R;
import kumpol.sunteen.co.th.sunteenfriend.utility.MyAlertDialog;

public class registerfragment extends Fragment {
    //    Explicit
    private Uri uri;
    private ImageView imageView;
    private boolean aBoolean = true;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        create toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please fill all Blank");

//        Setup Navigator Icon

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();

            }

        });

        setHasOptionsMenu(true);

//        picture Controller
        pictureController();
    } //Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {

            uploadAndUpdateValue();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadAndUpdateValue() {
        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
        EditText nameEditText = getView().findViewById(R.id.ediName);
        EditText userEditText = getView().findViewById(R.id.ediUser);
        EditText passEditText = getView().findViewById(R.id.ediPassword);

//        GetValue
        String nameString = nameEditText.getText().toString().trim();
        String userString = userEditText.getText().toString().trim();
        String passwordString = passEditText.getText().toString().trim();


//        Check choose image and space
        if (aBoolean) {
            //non choose image
            myAlertDialog.normatDialog("Non choose image", "Please choose image");
        } else if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
            myAlertDialog.normatDialog("Please fill all blank", "Please fill all");
        } else {
//            No space
            uploadImage();

        }


    }//Upload and Update

    private void uploadImage() {
        //Find path image
        String pathString = null;
        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, strings, null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            pathString = cursor.getString(index);
        } else {
            pathString = uri.getPath();
        }

        Log.d("8JulyV1", "Path ==> " + pathString);







    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
//            Success choose image
            uri = data.getData();
            aBoolean = false;
//            Show Image On Imageview
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Please choose image", Toast.LENGTH_SHORT).show();
        }
    }

    private void pictureController() {
        imageView = getView().findViewById(R.id.imvPicture);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent to Other App
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please choose app view image"), 1);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
