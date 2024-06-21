package movie.user.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.mapper.Mapper.Null;

import movie.user.entity.UserAttribute;
import movie.user.entity.UserAttributeExample;
import movie.user.mapper.UserAttributeMapper;

@Service
public class UserService {

	@Autowired
	private UserAttributeMapper userAttributeMapper;

	public boolean isFirst(Jwt jwt){
		UserAttributeExample userAttributeExample = new UserAttributeExample();
		userAttributeExample.createCriteria().andUserIdEqualTo(jwt.getClaimAsString("sub"));
		int i=0;
		List<UserAttribute> userAttributes = userAttributeMapper.selectByExample(userAttributeExample);
		// System.out.println(userAttributes);
		// System.out.println(userAttributeMapper.selectByExample(userAttributeExample).get(0).getName());
		// System.out.println(userAttributeMapper.selectByExample(userAttributeExample).get(0).getName().equals("first"));
		// System.out.println("first");
		// System.out.println(userAttributeMapper.selectByExample(userAttributeExample).get(1).getName());
		// System.out.println(userAttributeMapper.selectByExample(userAttributeExample).get(1).getName() == "first");
		while(!userAttributes.isEmpty()){
			if (userAttributes.get(i).getName().equals("first")){
				if (Integer.parseInt(userAttributes.get(i).getValue()) == 0) {
					return false;
				}
				else
					return true;
			}
				i++;
				
		}
		return true;
	}
}
