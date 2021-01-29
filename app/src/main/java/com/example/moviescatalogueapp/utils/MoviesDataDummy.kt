package com.example.moviescatalogueapp.utils

import com.example.moviescatalogueapp.data.model.CountriesEntity
import com.example.moviescatalogueapp.data.model.CrewEntity
import com.example.moviescatalogueapp.data.model.LanguageEntity
import com.example.moviescatalogueapp.data.model.MoviesEntity
import com.example.moviescatalogueapp.data.source.response.*

object MoviesDataDummy {

    fun generateDummyMovies(): List<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()
        val base_url = "https://image.tmdb.org/t/p/w500"

        movies.add(
            MoviesEntity(
                529203,
                "The Croods: A New Age",
                "$base_url/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
                "After leaving their cave, the Croods encounter their biggest threat since leaving: another family called the Bettermans, who claim and show to be better and evolved. Grug grows suspicious of the Betterman parents, Phil and Hope,  as they secretly plan to break up his daughter Eep with her loving boyfriend Guy to ensure that their daughter Dawn has a loving and smart partner to protect her.",
                "en",
                emptyList(),
                "2020-11-25",
                1937.566,
                8.1,
                420,
                65000000,
                35930000,
                "The future ain't what it used to be.",
                "Released",
                emptyList(),
                "",
                emptyList(),
                "",
                "",
                "",
                emptyList()
            )
        )
        movies.add(
            MoviesEntity(
                590706,
                "Jiu Jitsu",
                "$base_url/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                "en",
                emptyList(),
                "2020-11-20",
                1432.957,
                5.7,
                174,
                23000000,
                74000000,
                "From the darkness, the ultimate fighter rises.",
                "Released",
                emptyList(),
                "",
                emptyList(),
                "",
                "",
                "",
                emptyList()
            )
        )
        movies.add(
            MoviesEntity(
                577922,
                "Tenet",
                "$base_url/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                emptyList(),
                "10/05/2018",
                25.12,
                7.4,
                200,
                433888866,
                36000000,
                "",
                "",
                emptyList(),
                "",
                emptyList(),
                "",
                "",
                "",
                emptyList()
            )
        )

        return movies
    }

    fun generateDummyTvShows(): List<MoviesEntity> {
        val tvshows = ArrayList<MoviesEntity>()
        val base_url = "https://image.tmdb.org/t/p/w500"

        tvshows.add(
            MoviesEntity(
                82856,
                "The Mandalorian",
                "$base_url/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "en",
                emptyList(),
                "",
                25.12,
                7.4,
                200,
                0,
                0,
                "Bounty hunting is a complicated profession.",
                "Returning Series",
                emptyList(),
                "",
                emptyList(),
                "2019-11-12",
                "2020-12-18",
                "Scripted",
                generateDummyCreator()
            )
        )
        tvshows.add(
            MoviesEntity(
                97180,
                "Selena: The Series",
                "$base_url/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "en",
                emptyList(),
                "Bradley Cooper",
                25.12,
                7.4,
                200,
                0,
                0,
                "",
                "",
                emptyList(),
                "",
                emptyList(),
                "10/05/2018",
                "",
                "",
                generateDummyCreator()
            )
        )

        return tvshows
    }

    fun generateDummyCredits(): List<CrewEntity> {
        val crew = ArrayList<CrewEntity>()

        crew.add(
            CrewEntity(
                1450348,
                "Joel Crawford",
                "Director"
            )
        )
        crew.add(
            CrewEntity(
                41355,
                "Dimitri Logothetis",
                "Director"
            )
        )
        crew.add(
            CrewEntity(
                1450348,
                "Joel Crawford",
                "Director"
            )
        )

        return crew
    }

    fun generateDummyCountry(): List<CountriesEntity> {
        val country = ArrayList<CountriesEntity>()

        country.add(
            CountriesEntity(
                "US",
                "United States of America"
            )
        )
        country.add(
            CountriesEntity(
                "ES",
                "Spain"
            )
        )
        country.add(
            CountriesEntity(
                "KR",
                "South Korea"
            )
        )
        country.add(
            CountriesEntity(
                "RU",
                "Russia"
            )
        )
        country.add(
            CountriesEntity(
                "JP",
                "Japan"
            )
        )

        return country
    }

    fun generateDummyLanguage(): List<LanguageEntity> {
        val language = ArrayList<LanguageEntity>()

        language.add(
            LanguageEntity(
                "en",
                "English",
                "English"
            )
        )
        language.add(
            LanguageEntity(
                "es",
                "Spanish",
                "Español"
            )
        )
        language.add(
            LanguageEntity(
                "ko",
                "Korean",
                "한국어/조선말"
            )
        )
        language.add(
            LanguageEntity(
                "ru",
                "Russian",
                "Pусский"
            )
        )
        language.add(
            LanguageEntity(
                "ja",
                "Japanese",
                "日本語"
            )
        )

        return language
    }

    fun generateDummyCreator(): List<CreatedByItem> {
        val createdBy = ArrayList<CreatedByItem>()

        createdBy.add(
            CreatedByItem(
                15277,
                "Jon Favreau"
            )
        )

        return createdBy
    }


    // Remote Data Dummy
    fun generateRemoteDummyMovies(): List<MoviesObject> {
        val movies = ArrayList<MoviesObject>()
        val base_url = "https://image.tmdb.org/t/p/w500"

        movies.add(
            MoviesObject(
                529203,
                "The Croods: A New Age",
                "$base_url/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
                "After leaving their cave, the Croods encounter their biggest threat since leaving: another family called the Bettermans, who claim and show to be better and evolved. Grug grows suspicious of the Betterman parents, Phil and Hope,  as they secretly plan to break up his daughter Eep with her loving boyfriend Guy to ensure that their daughter Dawn has a loving and smart partner to protect her.",
                "en",
                emptyList(),
                "2020-11-25",
                1937.566,
                8.1,
                420,
                65000000,
                35930000,
                "The future ain't what it used to be.",
                "Released",
                emptyList(),
                "",
                emptyList(),
                "",
                "",
                "",
                emptyList()
            )
        )
        movies.add(
            MoviesObject(
                590706,
                "Jiu Jitsu",
                "$base_url/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                "en",
                emptyList(),
                "2020-11-20",
                1432.957,
                5.7,
                174,
                23000000,
                74000000,
                "From the darkness, the ultimate fighter rises.",
                "Released",
                emptyList(),
                "",
                emptyList(),
                "",
                "",
                "",
                emptyList()
            )
        )

        return movies
    }

    fun generateRemoteDummyTvShows(): List<MoviesObject> {
        val tvshows = ArrayList<MoviesObject>()
        val base_url = "https://image.tmdb.org/t/p/w500"

        tvshows.add(
            MoviesObject(
                82856,
                "The Mandalorian",
                "$base_url/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "en",
                emptyList(),
                "",
                25.12,
                7.4,
                200,
                0,
                0,
                "Bounty hunting is a complicated profession.",
                "Returning Series",
                emptyList(),
                "",
                listOf("US"),
                "2019-11-12",
                "2020-12-18",
                "Scripted",
                generateDummyCreator()
            )
        )

        return tvshows
    }

}