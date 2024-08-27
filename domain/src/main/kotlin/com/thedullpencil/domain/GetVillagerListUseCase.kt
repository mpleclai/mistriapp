import SortField.NAME
import com.thedullpencil.data.model.Villager
import com.thedullpencil.data.repository.VillagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetVillagerListUseCase @Inject constructor(
    private val villagersRepository: VillagerRepository,
) {
    /**
     * Returns a list of villagers
     *
     * @param sortBy - the field used to sort the list items. Default NONE = no sorting.
     */
    operator fun invoke(sortBy: SortField = NAME): Flow<List<Villager>> {
        val villagerList = villagersRepository.getVillagers()
        return when (sortBy) {
            NAME -> villagerList.transform { it.sortedBy { it.name } }
            else -> villagerList
        }
    }
}

enum class SortField {
    NONE,
    NAME,
}