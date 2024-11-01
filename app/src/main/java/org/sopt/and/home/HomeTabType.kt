package org.sopt.and.home

import androidx.annotation.StringRes
import org.sopt.and.R

enum class HomeTabType(
    @StringRes val titleRes: Int
) {
    NEW_CLASSIC(R.string.home_tab_new_classic),
    DRAMA(R.string.home_tab_drama),
    ENTERTAIN(R.string.home_tab_entertain),
    MOVIE(R.string.home_tab_movie),
    ANIMATION(R.string.home_tab_animation),
    GLOBAL(R.string.home_tab_global),
    DOCUMENTARY(R.string.home_tab_documentary),
    KIDS(R.string.home_tab_kids)
}