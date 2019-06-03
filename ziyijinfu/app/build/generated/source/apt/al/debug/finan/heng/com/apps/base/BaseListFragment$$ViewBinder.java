// Generated code from Butter Knife. Do not modify!
package finan.heng.com.apps.base;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BaseListFragment$$ViewBinder<T extends finan.heng.com.apps.base.BaseListFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690132, "field 'listView'");
    target.listView = finder.castView(view, 2131690132, "field 'listView'");
    view = finder.findRequiredView(source, 2131690031, "field 'progressView'");
    target.progressView = finder.castView(view, 2131690031, "field 'progressView'");
    view = finder.findRequiredView(source, 2131690133, "field 'ivEmptyview'");
    target.ivEmptyview = finder.castView(view, 2131690133, "field 'ivEmptyview'");
  }

  @Override public void unbind(T target) {
    target.listView = null;
    target.progressView = null;
    target.ivEmptyview = null;
  }
}
