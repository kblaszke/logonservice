package pl.blaszak.aws.logonservice.couchbase.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import pl.blaszak.aws.logonservice.couchbase.model.User;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "user")
public interface UserRepository extends CouchbasePagingAndSortingRepository<User, String> {


}
