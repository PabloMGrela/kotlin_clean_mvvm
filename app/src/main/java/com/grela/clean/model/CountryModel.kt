package com.grela.clean.model

import com.google.android.gms.maps.model.LatLng
import com.grela.clean.R
import com.grela.domain.model.CountryDomainEntity
import com.grela.domain.model.LeagueDomainEntity

data class CountryModel(
    val name: String,
    val flag: String,
    val continent: String,
    val location: LatLng,
    val leagues: List<LeagueModel>
)

data class LeagueModel(
    val name: String,
    val logo: String,
    val icon: Int?
)

fun CountryDomainEntity.toCountryModel() = CountryModel(name, image, continent, LatLng(lat.toDouble(), lon.toDouble()), leagueWrapper.toLeagueModelList())

fun List<CountryDomainEntity>.toCountryModelList() = map { it.toCountryModel() }

fun LeagueDomainEntity.toLeagueModel() = LeagueModel(name, logo, if (isCup) R.drawable.trophy else null)

fun List<LeagueDomainEntity>.toLeagueModelList() = map { it.toLeagueModel() }
