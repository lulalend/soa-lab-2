import styles from './styles.module.css';
import {FiltersItem} from "@/app/components/FiltersItem";
import {Button} from "@/app/components/Button";
import {Property} from "@/app/types/property";

type FiltersProps = {
    onClose: () => void
}

export const Filters = ({ onClose }: FiltersProps) => {
    return (
        <form className={styles.container}>
            <h2 className={styles.title}>Filters</h2>
            <FiltersItem property={Property.ID} validate={{type: 'number', min: 1}}/>
            <FiltersItem property={Property.NAME} validate={{type: 'text', minLength: 1}}/>
            <FiltersItem property={Property.DESCRIPTION} validate={{type: 'text'}}/>
            <FiltersItem property={Property.COORDINATE_X} validate={{type: 'number', step: 1}}/>
            <FiltersItem property={Property.COORDINATE_Y} validate={{type: 'number', min: -439}}/>
            <FiltersItem property={Property.CREATION_DATE} validate={{type: 'text'}}/>
            <FiltersItem property={Property.NUMBER_OF_PARTICIPANTS} validate={{type: 'number', min: 1, step: 1}}/>
            <FiltersItem property={Property.GENRE} validate={{type: 'text'}}/>
            <FiltersItem property={Property.PERSON_NAME} validate={{type: 'text', minLength: 1}}/>
            <div className='buttons'>
                <Button style='cancel' size='m' onClick={onClose}>
                    Cancel
                </Button>
                <Button style='primary' size='m' submit>
                    Find
                </Button>
            </div>
        </form>
    )
}