package com.ke.matatu;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author stuart
 * 
 *         References:
 *         http://pareshnmayani.wordpress.com/tag/android-custom-listview
 *         -example/ and
 *         http://www.vogella.de/articles/AndroidListView/article.html
 */
public class Ma3ListAdapter extends ArrayAdapter<Model> {

	private Context context;
	private List<Model> list;
	private LayoutInflater inflater;

	public Ma3ListAdapter(Context context, List<Model> list) {
		super(context, R.layout.custom_list_item, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected ImageView icon;
		protected TextView title;
		protected TextView body;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Model getItem(int position) {
		return list.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.custom_list_item, null);

			holder = new ViewHolder();
			holder.icon = (ImageView) convertView
					.findViewById(R.id.list_item_icon);
			holder.title = (TextView) convertView
					.findViewById(R.id.list_item_title);
			holder.body = (TextView) convertView
					.findViewById(R.id.list_item_description);
			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();

		Model model = (Model) list.get(position);
		holder.icon.setImageBitmap(getImage(model.getIcon()));
		holder.title.setText(model.getTitle());
		holder.body.setText(model.getBody());

		return convertView;
	}

	/**
	 * Sources: http://en.androidwiki.com/wiki/Loading_images_from_a_remote_server
	 * 	http://savagelook.com/blog/android/display-images-from-the-internet-in-android
	 * 	http://android-er.blogspot.com/2010/06/load-imageview-with-bitmap-from.html
	 * 
	 * @param iconURL
	 * @return
	 */
	private Bitmap getImage(String iconURL) {
		Bitmap image = null;
		
		try {
			URL url = new URL(iconURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream is = connection.getInputStream();
			
			image = BitmapFactory.decodeStream(is);
		} catch (MalformedURLException e) {
			Log.d("ListImageMalformedURLException", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("ListImageIOException", e.getMessage());
		}
		
		return image;
	}

}
